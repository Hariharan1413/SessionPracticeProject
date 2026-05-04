package org.example.services;

import org.example.colleague.Colleague;
import org.example.loaninterface.Notification;

/**
 * Colleague: sends loan-decision notifications.
 * Renamed method to clearly express side-effect intent.
 */
public class NotificationService extends Colleague implements Notification {

    @Override
    public void sendLoanStatus(String userName, String status) {
        System.out.println(userName + " — " + status);
    }
}
