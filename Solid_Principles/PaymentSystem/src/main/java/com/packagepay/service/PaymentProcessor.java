package com.packagepay.service;

import com.packagepay.model.Payment;

public class PaymentProcessor {
    Payment payment;

    public PaymentProcessor(Payment payment) {
       this.payment = payment;
    }
    public void processPay() {
       payment.pay();
    }
}
