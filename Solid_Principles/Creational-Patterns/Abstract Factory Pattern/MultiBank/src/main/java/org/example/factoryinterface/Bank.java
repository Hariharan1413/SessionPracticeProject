package org.example.factoryinterface;

public interface Bank {
    Account getAccount();
    CreditCard getCard();
    Loan applyLoan();
}
