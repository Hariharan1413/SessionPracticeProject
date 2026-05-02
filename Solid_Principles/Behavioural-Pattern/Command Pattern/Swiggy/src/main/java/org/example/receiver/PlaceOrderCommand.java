package org.example.receiver;

import org.example.commandpattern.Command;
import org.example.usecase.PlaceOrder;

public class PlaceOrderCommand implements Command {
    private PlaceOrder placeOrder;

    public PlaceOrderCommand(PlaceOrder placeOrder){
        this.placeOrder = placeOrder;
    }
    @Override
    public void executeCommand() {
        placeOrder.placeToOrder();
    }

    @Override
    public String CommandDetails() {
        return "PlacedOrder Id is "+ placeOrder.getProductId() ;
    }
}
