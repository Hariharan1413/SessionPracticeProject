package org.example.paymenytype;

import org.example.template.Payment;

public class DebitCard extends Payment {

    public DebitCard(String cardNumber) {
        super(cardNumber);
    }

    @Override
    protected void processPayment() {
        System.out.println("Debit card process make money ready");
    }

    @Override
    protected void sendNotification() {
        System.out.println("Sent notification to User via email");
    }
}
