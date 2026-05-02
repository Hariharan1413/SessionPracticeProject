package org.example;

public class Subscriber implements Observer {
    @Override
    public void update(String message) {
        System.out.println("Subscriber received: " + message);
    }
}
