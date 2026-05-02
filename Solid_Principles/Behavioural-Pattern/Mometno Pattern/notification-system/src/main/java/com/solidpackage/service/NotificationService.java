package com.solidpackage.service;

public class NotificationService {

    private MessageService msgService ;

    public NotificationService(MessageService msgServ) {
          this.msgService = msgServ;
    }
    
    public void runMethod() {
        msgService.sendMessage();
    }
}
