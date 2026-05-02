package org.example.usecase;

public class PlaceOrder {
    private String productId;

    public void setOrderId(String id) {
        this.productId = id;
    }

    public void placeToOrder() {
        System.out.println("Order id "+productId+" Successfully Placed ");
    }

    public String getProductId() {
        return productId;
    }
}
