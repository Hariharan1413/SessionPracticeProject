package org.example;

public class SMSNotifier implements Observer {
    private long mobileNumber;

    public SMSNotifier(long mobileNumber) {
        this.mobileNumber = mobileNumber;
    }

    @Override
    public void updateOrderStatus(String newStatus) {
        System.out.println("SMSNotification current status : " + newStatus + " of " + mobileNumber);
    }
}
