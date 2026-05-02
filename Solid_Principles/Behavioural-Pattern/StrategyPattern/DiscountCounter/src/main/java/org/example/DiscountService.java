package org.example;

// Context class - delegates discount calculation to the strategy
public class DiscountService {
    private OrderDiscount ordDisc;

    public DiscountService(OrderDiscount ordDisc) {
        this.ordDisc = ordDisc;
    }

    // Allows changing strategy at runtime (dynamic switching)
    public void setDiscountStrategy(OrderDiscount ordDisc) {
        this.ordDisc = ordDisc;
    }

    public double productDiscAmt() {
        return ordDisc.discountAmt();
    }
}
