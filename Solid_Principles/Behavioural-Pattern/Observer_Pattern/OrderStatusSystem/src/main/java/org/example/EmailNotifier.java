package org.example;

public class EmailNotifier implements Observer {
    private String emailId;

    public EmailNotifier(String emailId) {
        this.emailId = emailId;
    }

    @Override
    public void updateOrderStatus(String newStatus) {
        System.out.println("EmailNotification current status : " + newStatus + " of " + emailId);
    }
}
