package org.example;

import java.util.ArrayList;
import java.util.List;

public class OrderService {
    private List<Observer> clientList;
    private String updatedStatus;

    public OrderService() {
        clientList = new ArrayList<>();
    }

    public void addUser(Observer user) {
        if (user != null) {
            clientList.add(user);
        }
    }

    public void removeUser(Observer user) {
        clientList.remove(user);
    }

    public String getUpdatedStatus() {
        return updatedStatus;
    }

    public void notifyToUsers(String newStatus) {
        this.updatedStatus = newStatus;
        // Iterate over a snapshot to avoid ConcurrentModificationException
        List<Observer> snapshot = new ArrayList<>(clientList);
        for (Observer obsr : snapshot) {
            obsr.updateOrderStatus(newStatus);
        }
    }
}
