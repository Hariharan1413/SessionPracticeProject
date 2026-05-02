package org.example;

import org.example.invoker.CartService;
import org.example.receiver.CancelOrderCommand;
import org.example.receiver.ManageOrderHistory;
import org.example.receiver.PlaceOrderCommand;
import org.example.usecase.CancelOrder;
import org.example.usecase.PlaceOrder;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    public static void main(String[] args) {

        PlaceOrder placeOrder = new PlaceOrder();
        placeOrder.setOrderId("As12123");
        PlaceOrderCommand placeOrderCommand = new PlaceOrderCommand(placeOrder);
        CartService service = new CartService();
        service.setCommand(placeOrderCommand);
        service.moveToActionOrder();

        CancelOrder cancelOrder = new CancelOrder();
        cancelOrder.setOrderId("CAN14234");
        CancelOrderCommand cancelOrderCommand = new CancelOrderCommand(cancelOrder);
        service.setCommand(cancelOrderCommand);
        service.moveToActionOrder();

        service.getOrderHistory();
    }
}