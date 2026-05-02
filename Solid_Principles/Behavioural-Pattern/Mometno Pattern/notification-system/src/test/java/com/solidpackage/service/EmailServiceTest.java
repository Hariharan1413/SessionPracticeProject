package com.solidpackage.service;

import org.junit.jupiter.api.Test;
import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import static org.junit.jupiter.api.Assertions.*;

public class EmailServiceTest {

    @Test
    void testSendMessage_printsEmailOutput() {
        ByteArrayOutputStream outContent = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outContent));

        EmailService emailService = new EmailService();
        emailService.sendMessage();

        assertEquals("Message sent through Email", outContent.toString().trim());

        System.setOut(System.out);
    }

    @Test
    void testImplementsMessageService() {
        EmailService emailService = new EmailService();
        assertTrue(emailService instanceof MessageService);
    }
}
