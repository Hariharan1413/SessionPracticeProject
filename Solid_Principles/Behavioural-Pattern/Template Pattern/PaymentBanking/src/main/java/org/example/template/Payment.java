package org.example.template;

abstract public class Payment {

    protected String paymentId;

    public Payment(String paymentId) {
        this.paymentId = paymentId;
    }

    public final void MakePayment() {
        validateUser();
        checkBalance();
        processPayment();
        generateReceipt();
        sendNotification();
    }

     void validateUser() {
        if (paymentId != null && !paymentId.isEmpty()) {
            System.out.println("User Validated Successfully | ID: " + paymentId);
        } else {
            System.out.println("Validation Failed! Payment ID is invalid or empty.");
        }
    }

     void checkBalance() {
        System.out.println("Balaced have money");
    }

     void generateReceipt() {
        System.out.println("Take a receipt before leave");
    }

     protected abstract void processPayment() ;

     protected abstract void sendNotification() ;
}
