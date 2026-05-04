package org.example.loaninterface;

/**
 * Colleague contract for sending notifications.
 */
public interface Notification {
    void sendLoanStatus(String userName, String status);
}

