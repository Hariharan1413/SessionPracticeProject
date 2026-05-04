# Loan Processing — Mediator Pattern Workflow

---

## 🔴 BEFORE (Violations)

Colleagues were created **inside** the mediator. No interfaces. Stateful `user` field.

```
 ┌──────────────────────────────────────────────────────────┐
 │                        Main.java                         │
 │                                                          │
 │  user1 ──► LoanService(user1)  ◄── user stored as field  │
 │            loanService.processLoan()                     │
 │            loanService.setUser(user2) ◄── mutates state  │
 │            loanService.processLoan()                     │
 └────────────────────────┬─────────────────────────────────┘
                          │
                          ▼
 ┌──────────────────────────────────────────────────────────┐
 │              LoanService (Mediator)                      │
 │                                                          │
 │  ❌ new CreditScoreService()   ◄── tight coupling        │
 │  ❌ new IncomeCheckService()   ◄── tight coupling        │
 │  ❌ new NotificationService()  ◄── tight coupling        │
 │  ❌ private User user          ◄── mutable state         │
 │                                                          │
 │  processLoan() {                                         │
 │     creditService.checkScore(user.getCreditScore())      │
 │     incomeCheckService.checkUserIncome(user.getIncome()) │
 │     notify.getLoanApprovalStatus(...)                    │
 │  }                                                       │
 └────┬──────────────────┬──────────────────┬───────────────┘
      │                  │                  │
      ▼                  ▼                  ▼
 ┌──────────┐   ┌────────────────┐   ┌──────────────────┐
 │ CreditSc │   │ IncomeCheckSvc │   │ NotificationSvc  │
 │ oreSvc   │   │                │   │                  │
 │ score>600│   │ salary>25000   │   │ getLoanApproval  │
 │          │   │                │   │ Status() ◄── bad │
 │ no iface │   │ no interface   │   │ naming (getter?) │
 │ no mediator  │ no mediator    │   │ no mediator ref  │
 │ reference│   │ reference      │   │                  │
 └──────────┘   └────────────────┘   └──────────────────┘

 Problems:
  • Colleagues don't know about the mediator (no back-reference)
  • Mediator creates colleagues internally (not injectable/testable)
  • No interfaces → can't swap implementations
  • Stateful user field → thread-unsafe, hidden bugs
```

---

## 🟢 AFTER (Correct Mediator Pattern)

Colleagues injected via **interfaces**. Each colleague holds a **mediator reference**.
`processLoan(User)` is **stateless**.

```
 ┌──────────────────────────────────────────────────────────────┐
 │                          Main.java                           │
 │                                                              │
 │  1. Create colleague instances externally                    │
 │     creditSvc  = new CreditScoreService()                   │
 │     incomeSvc  = new IncomeCheckService()                    │  
 │     notifySvc  = new NotificationService()                   │
 │                                                              │
 │  2. Inject into mediator                                     │
 │     mediator = new LoanService(creditSvc, incomeSvc, notSvc) │
 │                                                              │
 │  3. Stateless calls                                          │
 │     mediator.processLoan(user1)  ◄── User passed per call    │
 │     mediator.processLoan(user2)  ◄── no setUser() needed     │
 └──────────────────────────┬───────────────────────────────────┘
                            │
                            ▼
 ┌──────────────────────────────────────────────────────────────┐
 │                                                              │
 │            «interface» LoanMediator                          │
 │            ─────────────────────────                         │
 │            + registerColleague(Colleague)                    │
 │            + processLoan(User)                               │
 │                                                              │
 └──────────────────────────┬───────────────────────────────────┘
                            │ implements
                            ▼
 ┌──────────────────────────────────────────────────────────────┐
 │               LoanService  (Concrete Mediator)               │
 │                                                              │
 │  Fields (all interfaces — no concrete types):                │
 │    ✅ CreditScoreCheck  creditScoreCheck                     │
 │    ✅ IncomeCheck        incomeCheck                         │
 │    ✅ Notification       notification                        │
 │                                                              │
 │  Constructor:                                                │
 │    ✅ Receives colleagues via injection (no "new" inside)    │
 │    ✅ Calls registerColleague() for each → sets mediator ref │
 │                                                              │
 │  processLoan(User user):          ◄── stateless              │
 │  ┌────────────────────────────────────────────────────┐      │
 │  │  Step 1: creditScoreCheck.checkScore(score)        │      │
 │  │  Step 2: incomeCheck.checkUserIncome(salary)       │      │
 │  │  Step 3: decideLoan(creditOk, incomeOk)            │      │
 │  │  Step 4: notification.sendLoanStatus(name, status) │      │
 │  └────────────────────────────────────────────────────┘      │
 └──────┬───────────────────┬───────────────────┬───────────────┘
        │                   │                   │
        │ registerColleague │ registerColleague  │ registerColleague
        │ sets mediator ▲   │ sets mediator ▲   │ sets mediator ▲
        ▼               │   ▼               │   ▼               │
 ┌─────────────┐  ┌─────────────────┐  ┌────────────────────┐
 │«Colleague»  │  │ «Colleague»     │  │ «Colleague»        │
 │CreditScore  │  │ IncomeCheck     │  │ Notification       │
 │Service      │  │ Service         │  │ Service            │
 ├─────────────┤  ├─────────────────┤  ├────────────────────┤
 │extends      │  │extends          │  │extends             │
 │ Colleague   │  │ Colleague       │  │ Colleague          │
 │implements   │  │implements       │  │implements          │
 │ CreditScore │  │ IncomeCheck     │  │ Notification       │
 │ Check       │  │                 │  │                    │
 ├─────────────┤  ├─────────────────┤  ├────────────────────┤
 │checkScore() │  │checkUserIncome()│  │sendLoanStatus()    │
 │MIN_SCORE=600│  │MIN_INCOME=25000 │  │                    │
 │             │  │                 │  │                    │
 │mediator ────┘  │mediator ────────┘  │mediator ───────────┘
 │(inherited)  │  │(inherited)      │  │(inherited)         │
 └─────────────┘  └─────────────────┘  └────────────────────┘
```

---

## 📐 Class Diagram

```
                    ┌─────────────────────┐
                    │  «interface»        │
                    │  LoanMediator       │
                    │─────────────────────│
                    │+ registerColleague()│
                    │+ processLoan(User)  │
                    └────────┬────────────┘
                             │ implements
                             ▼
                    ┌─────────────────────┐
                    │   LoanService       │
                    │  (Concrete Mediator)│
                    │─────────────────────│        ┌──────────────┐
                    │- creditScoreCheck ──┼───────►│«interface»   │
                    │- incomeCheck ───────┼──┐     │CreditScore   │
                    │- notification ──────┼┐ │     │Check         │
                    └─────────────────────┘│ │     └──────┬───────┘
                                           │ │            │ implements
              ┌────────────────────────────┘ │     ┌──────┴───────┐
              │    ┌─────────────────────────┘     │CreditScore   │
              │    │                               │Service       │
              │    │     ┌──────────────┐          └──────────────┘
              │    └────►│«interface»   │
              │          │IncomeCheck   │
              │          └──────┬───────┘
              │                 │ implements
              │          ┌──────┴───────┐
              │          │IncomeCheck   │
              │          │Service       │
              │          └──────────────┘
              │
              │  ┌──────────────┐
              └─►│«interface»   │
                 │Notification  │
                 └──────┬───────┘
                        │ implements
                 ┌──────┴───────┐
                 │Notification  │
                 │Service       │
                 └──────────────┘

    All three services extend:
                 ┌──────────────┐
                 │ «abstract»   │
                 │  Colleague   │
                 │──────────────│
                 │# mediator    │
                 │──────────────│
                 │+setMediator()│
                 └──────────────┘
```

---

## 🔄 Runtime Sequence (processLoan)

```
  Main              LoanService           CreditScore       IncomeCheck       Notification
   │                 (Mediator)             Service            Service           Service
   │                     │                    │                  │                  │
   │  processLoan(user)  │                    │                  │                  │
   │────────────────────►│                    │                  │                  │
   │                     │                    │                  │                  │
   │                     │  checkScore(680)   │                  │                  │
   │                     │───────────────────►│                  │                  │
   │                     │     true           │                  │                  │
   │                     │◄───────────────────│                  │                  │
   │                     │                    │                  │                  │
   │                     │  checkUserIncome(34000)               │                  │
   │                     │──────────────────────────────────────►│                  │
   │                     │     true                              │                  │
   │                     │◄──────────────────────────────────────│                  │
   │                     │                    │                  │                  │
   │                     │  decideLoan(true, true)               │                  │
   │                     │  → "Loan request Accepted"            │                  │
   │                     │                    │                  │                  │
   │                     │  sendLoanStatus("Cap", "Loan request Accepted")         │
   │                     │────────────────────────────────────────────────────────►│
   │                     │                    │                  │               prints
   │                     │                    │                  │                  │
   │◄────────────────────│                    │                  │                  │
   │                     │                    │                  │                  │

   KEY POINT: CreditScoreService, IncomeCheckService, and NotificationService
              NEVER talk to each other — all communication flows through LoanService.
```

---

## ✅ Mediator Pattern Rules Checklist

| Rule | Status | How |
|------|--------|-----|
| Colleagues don't know about each other | ✅ | Services have no imports of other services |
| All communication goes through mediator | ✅ | `LoanService.processLoan()` orchestrates everything |
| Colleagues hold a mediator reference | ✅ | `Colleague.mediator` field, set via `registerColleague()` |
| Mediator depends on abstractions | ✅ | Fields typed as `CreditScoreCheck`, `IncomeCheck`, `Notification` |
| Colleagues are injected, not created | ✅ | Constructor injection — no `new` inside mediator |
| Mediator is stateless per request | ✅ | `User` passed to `processLoan()`, not stored as field |

---

## 📁 Project Structure

```
src/main/java/org/example/
├── Main.java                        ← entry point, wires everything
├── colleague/
│   └── Colleague.java               ← abstract base (holds mediator ref)
├── loaninterface/
│   ├── LoanMediator.java            ← mediator contract
│   ├── CreditScoreCheck.java        ← colleague interface
│   ├── IncomeCheck.java             ← colleague interface
│   └── Notification.java            ← colleague interface
└── services/
    ├── CreditScoreService.java      ← colleague impl
    ├── IncomeCheckService.java      ← colleague impl
    ├── NotificationService.java     ← colleague impl
    ├── LoanService.java             ← concrete mediator
    └── User.java                    ← domain object

src/test/java/org/example/services/
    └── LoanServiceTest.java         ← 6 unit tests with stub colleagues
```

