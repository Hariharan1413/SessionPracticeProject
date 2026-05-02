package org.example.invoker;

import org.example.commandpattern.Command;
import org.example.receiver.ManageOrderHistory;

public class CartService {
    private Command command;
    private ManageOrderHistory orderHistory;

    public CartService() {
        orderHistory = new ManageOrderHistory();
    }

    public void setCommand(Command command) {
        this.command = command;
    }
    public void moveToActionOrder() {
        command.executeCommand();
        orderHistory.addOrderInHistory(command.CommandDetails());
    }

    public void getOrderHistory() {
        orderHistory.printOrderList();
    }
}
