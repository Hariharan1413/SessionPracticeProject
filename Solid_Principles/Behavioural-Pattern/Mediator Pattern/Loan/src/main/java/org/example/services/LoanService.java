package org.example.services;

import org.example.colleague.Colleague;
import org.example.loaninterface.CreditScoreCheck;
import org.example.loaninterface.IncomeCheck;
import org.example.loaninterface.LoanMediator;
import org.example.loaninterface.Notification;

/**
 * Concrete Mediator — orchestrates loan processing.
 *
 * Key Mediator-pattern properties:
 *  1. Depends only on colleague interfaces (CreditScoreCheck, IncomeCheck, Notification).
 *  2. Colleagues are injected (no "new" inside the mediator).
 *  3. Stateless per-request: User is passed to processLoan(), not stored as a field.
 *  4. Each colleague is registered with this mediator via registerColleague().
 */
public class LoanService implements LoanMediator {

    private final CreditScoreCheck creditScoreCheck;
    private final IncomeCheck incomeCheck;
    private final Notification notification;

    /**
     * Constructor injection — the mediator receives its colleagues as abstractions.
     */
    public LoanService(CreditScoreCheck creditScoreCheck,
                       IncomeCheck incomeCheck,
                       Notification notification) {
        this.creditScoreCheck = creditScoreCheck;
        this.incomeCheck = incomeCheck;
        this.notification = notification;

        // Register this mediator with each colleague so they can call back if needed.
        registerColleague((Colleague) creditScoreCheck);
        registerColleague((Colleague) incomeCheck);
        registerColleague((Colleague) notification);
    }

    @Override
    public void registerColleague(Colleague colleague) {
        colleague.setMediator(this);
    }

    /**
     * Stateless orchestration — no mutable user field.
     */
    @Override
    public void processLoan(User user) {
        boolean creditOk = creditScoreCheck.checkScore(user.getCreditScore());
        boolean incomeOk = incomeCheck.checkUserIncome(user.getIncome());
        String status = decideLoan(creditOk, incomeOk);
        notification.sendLoanStatus(user.getName(), status);
    }

    private String decideLoan(boolean creditOk, boolean incomeOk) {
        if (creditOk && incomeOk) {
            return "Loan request Accepted";
        }
        return "Loan request Rejected";
    }
}
