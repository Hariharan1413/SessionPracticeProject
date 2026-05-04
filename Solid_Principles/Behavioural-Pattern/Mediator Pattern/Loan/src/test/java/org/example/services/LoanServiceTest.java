package org.example.services;

import org.example.loaninterface.CreditScoreCheck;
import org.example.loaninterface.IncomeCheck;
import org.example.loaninterface.LoanMediator;
import org.example.loaninterface.Notification;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Tests for LoanService (concrete mediator).
 *
 * Uses simple stub implementations instead of a mocking framework
 * to show that the mediator depends only on interfaces.
 */
class LoanServiceTest {

    // ---- Stub colleagues ------------------------------------------------

    /** Stub that always returns a fixed credit-score result. */
    static class StubCreditScore extends org.example.colleague.Colleague implements CreditScoreCheck {
        private final boolean result;
        StubCreditScore(boolean result) { this.result = result; }
        @Override public boolean checkScore(int score) { return result; }
    }

    /** Stub that always returns a fixed income-check result. */
    static class StubIncomeCheck extends org.example.colleague.Colleague implements IncomeCheck {
        private final boolean result;
        StubIncomeCheck(boolean result) { this.result = result; }
        @Override public boolean checkUserIncome(double salary) { return result; }
    }

    /** Captures notifications so we can assert on them. */
    static class SpyNotification extends org.example.colleague.Colleague implements Notification {
        final List<String> messages = new ArrayList<>();
        @Override public void sendLoanStatus(String userName, String status) {
            messages.add(userName + ": " + status);
        }
    }

    // ---- Tests ----------------------------------------------------------

    private SpyNotification spy;

    @BeforeEach
    void setUp() {
        spy = new SpyNotification();
    }

    @Test
    void loanAccepted_whenCreditAndIncomePass() {
        LoanMediator mediator = new LoanService(
                new StubCreditScore(true),
                new StubIncomeCheck(true),
                spy);

        mediator.processLoan(new User("Alice", 700, 30000));

        assertEquals(1, spy.messages.size());
        assertTrue(spy.messages.get(0).contains("Accepted"));
    }

    @Test
    void loanRejected_whenCreditFails() {
        LoanMediator mediator = new LoanService(
                new StubCreditScore(false),
                new StubIncomeCheck(true),
                spy);

        mediator.processLoan(new User("Bob", 500, 30000));

        assertTrue(spy.messages.get(0).contains("Rejected"));
    }

    @Test
    void loanRejected_whenIncomeFails() {
        LoanMediator mediator = new LoanService(
                new StubCreditScore(true),
                new StubIncomeCheck(false),
                spy);

        mediator.processLoan(new User("Carol", 700, 10000));

        assertTrue(spy.messages.get(0).contains("Rejected"));
    }

    @Test
    void loanRejected_whenBothFail() {
        LoanMediator mediator = new LoanService(
                new StubCreditScore(false),
                new StubIncomeCheck(false),
                spy);

        mediator.processLoan(new User("Dave", 400, 10000));

        assertTrue(spy.messages.get(0).contains("Rejected"));
    }

    @Test
    void mediatorIsStateless_multipleUsersOnSameInstance() {
        LoanMediator mediator = new LoanService(
                new StubCreditScore(true),
                new StubIncomeCheck(true),
                spy);

        mediator.processLoan(new User("User1", 700, 30000));
        mediator.processLoan(new User("User2", 700, 30000));

        assertEquals(2, spy.messages.size());
        assertTrue(spy.messages.get(0).startsWith("User1"));
        assertTrue(spy.messages.get(1).startsWith("User2"));
    }

    @Test
    void colleaguesReceiveMediatorReference() {
        StubCreditScore credit = new StubCreditScore(true);
        StubIncomeCheck income = new StubIncomeCheck(true);

        LoanMediator mediator = new LoanService(credit, income, spy);

        // After construction, every colleague should hold the mediator reference
        assertSame(mediator, credit.mediator);
        assertSame(mediator, income.mediator);
        assertSame(mediator, spy.mediator);
    }
}

