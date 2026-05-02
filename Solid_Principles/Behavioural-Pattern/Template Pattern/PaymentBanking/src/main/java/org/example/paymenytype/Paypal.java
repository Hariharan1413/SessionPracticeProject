package org.example.paymenytype;

import org.example.template.Payment;

public class Paypal extends Payment {

    public Paypal(String paypalId) {
        super(paypalId);
    }

    @Override
    protected void processPayment() {
        System.out.println("Paypal payment process make money ready");
    }

    @Override
    protected void sendNotification() {
        System.out.println("Sent notification to User via Sms");
    }
}
