package org.example;

import java.util.ArrayList;
import java.util.List;

public class NotificationService {
    private String eventUpdate;
    private List<Observer> subsList;

    public NotificationService() {
        this("");
    }

    public NotificationService(String newUpdate) {
        subsList = new ArrayList<>();
        this.eventUpdate = newUpdate;
    }

    public void addSubscriber(Observer observer) {
        subsList.add(observer);
    }

    public void removeSubscriber(Observer observer) {
        subsList.remove(observer);
    }

    public void updateMsgEvent(String message) {
        this.eventUpdate = message;
        for (Observer e : subsList) {
            e.update(message);
        }
    }
}
