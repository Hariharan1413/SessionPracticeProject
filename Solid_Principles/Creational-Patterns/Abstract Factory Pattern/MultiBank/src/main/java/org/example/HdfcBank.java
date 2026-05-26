package org.example;

import org.example.factoryinterface.Account;
import org.example.factoryinterface.Bank;
import org.example.factoryinterface.CreditCard;
import org.example.factoryinterface.Loan;

public class HdfcBank implements Bank {



    @Override
    public Account getAccount() {
        return new Account() {
            @Override
            public void openAccount() {
                System.out.println("Account Created . Welcom to HDFC Bank");
            }
        };
    }

    @Override
    public CreditCard getCard() {
        return new CreditCard() {
            @Override
            public void issueCard() {
                System.out.println("Please get the HDFC Credit Card");
            }
        };
    }

    @Override
    public Loan applyLoan() {
        return new Loan() {
            @Override
            public void applyLoan() {
                System.out.println("Hdfc loan approved");
            }
        };
    }
}
