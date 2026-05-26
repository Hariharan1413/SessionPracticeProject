# Code Review: MultiBank Factory Pattern

## Issues Summary

| **Severity** | **Category** | **File** | **Location** | **Issue** | **Impact** | **Recommendation** |
|---|---|---|---|---|---|---|
| **HIGH** | Null Safety | `BankFactory.java` | Line 11 | `bankName.equals("HDFC")` throws `NullPointerException` if `bankName` is null | Runtime crash on null input; unhandled exception bubbles to caller | Add null check: `if (bankName == null \|\| bankName.isEmpty())` or use `Objects.equals(bankName, "HDFC")` |
| **MEDIUM** | Logic Error | `BankFactory.java` | Lines 11-15 | Unknown/case-mismatched bank names silently default to `ICICBank` (e.g., `"hdfc"`, `"SBI"`, `"xyz"` all become ICICI) | Silent business logic failure; wrong bank returned without warning; hard to debug | Throw `IllegalArgumentException` for unknown names, or make matching case-insensitive with `.equalsIgnoreCase()` |
| **MEDIUM** | Design | `BankFactory.java` | Line 8 | Shared mutable static field `private static Bank bank` accumulates state across calls | Potential concurrency issues in multi-threaded context; unnecessary coupling between calls | Remove static field; return new instance directly each time |
| **LOW** | Testing Gap | `src/test/java/` | (empty) | No unit tests for factory routing, null handling, or product creation | Edge cases unprotected from regression; no continuous validation | Add JUnit tests covering: valid names (HDFC, ICICI), unknown names, null input, case variants |
| **LOW** | Typo | `HdfcBank.java` | Line 17 | "Welcom" should be "Welcome" | Minor: user-facing message has spelling error | Fix typo in string literal |
| **LOW** | Typo | `ICICBank.java` | Line 14 | "Welcom" should be "Welcome" | Minor: user-facing message has spelling error | Fix typo in string literal |
| **LOW** | Variable Naming | `Main.java` | Line 13 | Variable `acount` should be `account` (typo) | Readability; inconsistent naming standard | Rename to `account` |

---

## Detailed Findings

### 1. NullPointerException Risk (HIGH)
**File**: `BankFactory.java:11`  
**Problem**: 
```java
public static Bank Selectbank(String bankName) {
    if(bankName.equals("HDFC")) {  // ← NPE if bankName is null
```
**Impact**: Crashes with `NullPointerException` on null input.  
**Fix**:
```java
if (bankName != null && bankName.equals("HDFC")) {
    bank = new HdfcBank();
} else if (bankName != null && "ICICI".equals(bankName)) {
    bank = new ICICBank();
} else {
    throw new IllegalArgumentException("Unknown bank: " + bankName);
}
```

---

### 2. Silent Default Behavior (MEDIUM)
**File**: `BankFactory.java:11-15`  
**Problem**: 
```java
if(bankName.equals("HDFC")) {
    bank = new HdfcBank();
} else {
    bank = new ICICBank();  // ← Any input that's not "HDFC" → ICICI
}
```
**Impact**: 
- `Selectbank("hdfc")` returns `ICICBank` (case-sensitive match fails)
- `Selectbank("SBI")` returns `ICICBank` (no SBI impl, but no error)
- Client code can't tell if the wrong bank was selected

**Fix**: Either throw exception or use case-insensitive matching:
```java
String normalizedName = bankName.trim().toUpperCase();
if ("HDFC".equals(normalizedName)) {
    bank = new HdfcBank();
} else if ("ICICI".equals(normalizedName)) {
    bank = new ICICBank();
} else {
    throw new IllegalArgumentException("Unsupported bank: " + bankName);
}
```

---

### 3. Unnecessary Static State (MEDIUM)
**File**: `BankFactory.java:8`  
**Problem**:
```java
private static Bank bank;  // ← Shared mutable state

public static Bank Selectbank(String bankName) {
    bank = new HdfcBank();  // ← Overwrites previous value
    return bank;
}
```
**Impact**: 
- Each call overwrites the previous `bank` instance
- If code caches the result or expects immutability, state can leak between calls
- Poor thread safety in concurrent environments

**Fix**: Remove static field and return directly:
```java
public static Bank Selectbank(String bankName) {
    if ("HDFC".equalsIgnoreCase(bankName)) {
        return new HdfcBank();
    } else if ("ICICI".equalsIgnoreCase(bankName)) {
        return new ICICBank();
    } else {
        throw new IllegalArgumentException("Unsupported bank: " + bankName);
    }
}
```

---

### 4. No Unit Tests (LOW)
**File**: `src/test/java/` is empty  
**Impact**: 
- No automated validation of factory behavior
- Edge cases (null, unknown banks) not covered
- Regressions can slip through undetected

**Recommended Tests**:
- `testSelectBankHDFC()` → verify returns `HdfcBank` instance
- `testSelectBankICICI()` → verify returns `ICICBank` instance
- `testSelectBankCaseInsensitive()` → verify lowercase/mixed case works
- `testSelectBankNullThrowsException()` → verify null input fails safely
- `testSelectBankUnknownThrowsException()` → verify unknown name fails safely

---

### 5. Minor Typos
| File | Line | Typo | Fix |
|---|---|---|---|
| `HdfcBank.java` | 17 | "Welcom" | "Welcome" |
| `ICICBank.java` | 14 | "Welcom" | "Welcome" |
| `Main.java` | 13 | `acount` | `account` |

---

## Code Quality Summary
- **Functionality**: Works for happy path but fragile to edge cases
- **Error Handling**: Missing; silently defaults or crashes
- **Design**: Extra static state; factory should stateless
- **Testing**: Completely absent (0 tests)
- **Style**: Minor typos; naming inconsistencies

## Recommended Action Priority
1. **Fix HIGH** null-safety issue → prevents runtime crashes
2. **Fix MEDIUM** silent-default behavior → prevents business logic errors
3. **Add tests** → protect against future regressions
4. **Clean typos** → improve code quality

