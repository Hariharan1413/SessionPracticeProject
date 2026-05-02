package org.example;

public class EmailNotifier implements Observer {
    private final String emailAddress;

    public EmailNotifier(String emailAddress) {
        this.emailAddress = emailAddress;
    }

    @Override
    public void update(String message) {
        System.out.println("Email sent to " + emailAddress + " : " + message);
    }
}

