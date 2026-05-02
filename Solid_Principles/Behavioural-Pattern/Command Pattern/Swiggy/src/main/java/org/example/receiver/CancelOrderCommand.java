package org.example.receiver;

import org.example.commandpattern.Command;
import org.example.usecase.CancelOrder;

public class CancelOrderCommand implements Command {
    private CancelOrder cancelOrder;
    public CancelOrderCommand(CancelOrder cancelOrder) {
        this.cancelOrder = cancelOrder;
    }

    @Override
    public void executeCommand() {
        cancelOrder.cancelOrder();
    }

    @Override
    public String CommandDetails() {
        return "CancelOrder Id is "+ cancelOrder.getProductId() ;
    }
}
