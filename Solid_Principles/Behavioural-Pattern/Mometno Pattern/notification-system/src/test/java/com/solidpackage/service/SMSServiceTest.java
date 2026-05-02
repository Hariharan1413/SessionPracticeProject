package com.solidpackage.service;

import org.junit.jupiter.api.Test;
import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import static org.junit.jupiter.api.Assertions.*;

public class SMSServiceTest {

    @Test
    void testSendMessage_printsSMSOutput() {
        ByteArrayOutputStream outContent = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outContent));

        SMSService smsService = new SMSService();
        smsService.sendMessage();

        assertEquals("Message sent through SMS", outContent.toString().trim());

        System.setOut(System.out);
    }

    @Test
    void testImplementsMessageService() {
        SMSService smsService = new SMSService();
        assertTrue(smsService instanceof MessageService);
    }
}
