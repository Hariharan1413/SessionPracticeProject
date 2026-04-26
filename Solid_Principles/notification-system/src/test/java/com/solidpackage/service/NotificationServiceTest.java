package com.solidpackage.service;

import org.junit.jupiter.api.Test;
import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import static org.junit.jupiter.api.Assertions.*;

public class NotificationServiceTest {

    @Test
    void testRunMethod_delegatesToMessageService() {
        boolean[] called = {false};
        MessageService mockService = () -> called[0] = true;

        NotificationService notificationService = new NotificationService(mockService);
        notificationService.runMethod();

        assertTrue(called[0], "sendMessage() should be called on the injected MessageService");
    }

    @Test
    void testConstructor_acceptsMessageService() {
        MessageService mockService = () -> {};
        NotificationService notificationService = new NotificationService(mockService);
        assertNotNull(notificationService);
    }

    @Test
    void testRunMethod_withEmailService() {
        ByteArrayOutputStream outContent = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outContent));

        NotificationService notificationService = new NotificationService(new EmailService());
        notificationService.runMethod();

        assertEquals("Message sent through Email", outContent.toString().trim());

        System.setOut(System.out);
    }

    @Test
    void testRunMethod_withSMSService() {
        ByteArrayOutputStream outContent = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outContent));

        NotificationService notificationService = new NotificationService(new SMSService());
        notificationService.runMethod();

        assertEquals("Message sent through SMS", outContent.toString().trim());

        System.setOut(System.out);
    }
}
