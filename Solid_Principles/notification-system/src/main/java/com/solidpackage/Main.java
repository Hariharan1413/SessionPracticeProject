package com.solidpackage;
import com.solidpackage.service.MessageService;
import com.solidpackage.service.EmailService;
import com.solidpackage.service.NotificationService;

public class Main {

    public static void main(String[] args) {

        MessageService email = new EmailService();
        NotificationService mainServ = new NotificationService(email);
        mainServ.runMethod();
    }
}
