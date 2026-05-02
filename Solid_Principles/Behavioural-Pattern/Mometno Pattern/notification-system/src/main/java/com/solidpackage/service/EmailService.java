package com.solidpackage.service;

public class EmailService implements MessageService{
    
    @Override
    public void sendMessage() {
        System.out.println("Message sent through Email");
    }
}
