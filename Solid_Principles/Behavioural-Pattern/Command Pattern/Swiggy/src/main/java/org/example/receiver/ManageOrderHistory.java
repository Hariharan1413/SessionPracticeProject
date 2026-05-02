package  org.example.receiver;

import java.util.ArrayList;
import java.util.List;

public class ManageOrderHistory  {

    private List<String> orderHistory;

    public  ManageOrderHistory() {
        orderHistory = new ArrayList<>();
    }

    public void addOrderInHistory(String orderDetails) {
        orderHistory.add(orderDetails);
    }

    public void printOrderList() {
        for(String trans : orderHistory) {
            System.out.println(trans);
        }
    }

}