package org.example.usecase;

public class CancelOrder {

    private String productId;

    public void setOrderId(String id) {
        this.productId = id;
    }

    public void cancelOrder() {
        System.out.println("Order id "+productId+" Cancelled ");
    }

    public String getProductId() {
        return productId;
    }
}
