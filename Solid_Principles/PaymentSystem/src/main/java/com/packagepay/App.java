package com.packagepay;

import com.packagepay.model.Payment;
import com.packagepay.model.UPIPayment;
import com.packagepay.service.PaymentProcessor;
import com.packagepay.service.GenerateReceipt;
import com.packagepay.repository.TransactionRepo;

public class App {
    public static void main(String[] args) {
        Payment upiPayment = new UPIPayment();
        PaymentProcessor processor = new PaymentProcessor(upiPayment);
        processor.processPay();
        GenerateReceipt receipt = new GenerateReceipt();
        receipt.generatePayReceipt(1000,upiPayment.desc());

        TransactionRepo repo = new TransactionRepo();
        repo.savePay(1000,upiPayment.desc());
    }
}
