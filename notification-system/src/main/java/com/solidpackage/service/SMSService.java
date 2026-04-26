package com.solidpackage.service;

public class SMSService implements MessageService{
    @Override
    public void sendMessage() {
        System.out.println("Message sent through SMS");
    }
}
