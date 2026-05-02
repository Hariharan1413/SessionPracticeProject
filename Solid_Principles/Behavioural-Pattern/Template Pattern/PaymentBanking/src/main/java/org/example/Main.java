package org.example;

import org.example.paymenytype.DebitCard;
import org.example.paymenytype.Paypal;
import org.example.template.Payment;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    public static void main(String[] args) {

        System.out.println("--- Debit Card Payment ---");
        Payment card = new DebitCard("CARD-9876543210");
        card.MakePayment();

        System.out.println("\n--- PayPal Payment ---");
        Payment paypal = new Paypal("PAYPAL-USER@example.com");
        paypal.MakePayment();

        System.out.println("\n--- Invalid Payment ID Test ---");
        Payment invalid = new DebitCard("");
        invalid.MakePayment();
    }
}