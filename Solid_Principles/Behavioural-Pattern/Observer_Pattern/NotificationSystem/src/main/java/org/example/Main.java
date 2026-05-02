package org.example;

public class Main {
    public static void main(String[] args) {

        // 1. Create the Subject (Publisher)
        NotificationService service = new NotificationService();

        // 2. Create Concrete Observers
        Subscriber subscriber1 = new Subscriber();           // Old observer — prints to console
        EmailNotifier emailUser = new EmailNotifier("hari@example.com"); // New observer — simulates email

        // 3. Register both observers
        service.addSubscriber(subscriber1);
        service.addSubscriber(emailUser);

        // 4. Publish a notification — both observers get notified
        System.out.println("--- First Notification ---");
        service.updateMsgEvent("New offer available!");

        // 5. Remove one observer and notify again
        service.removeSubscriber(subscriber1);

        System.out.println("\n--- Second Notification (after removing Subscriber) ---");
        service.updateMsgEvent("Flash sale starts now!");
    }
}