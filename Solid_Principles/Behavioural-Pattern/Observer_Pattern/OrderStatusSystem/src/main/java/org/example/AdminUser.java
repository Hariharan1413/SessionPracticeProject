package org.example;

public class AdminUser implements  Observer {
    @Override
    public void updateOrderStatus(String newStatus) {
        System.out.println("Admin Dashboard : "+newStatus);
    }
}
