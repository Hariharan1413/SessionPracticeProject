package org.example;

// Concrete Strategy - Premium User Discount
public class PremiumUserDiscount implements OrderDiscount {
    private final DiscountCalculator discountCalculator;

    public PremiumUserDiscount(int productPrice, String premiumDiscount) {
        discountCalculator = new DiscountCalculator(premiumDiscount, productPrice);
    }

    @Override
    public double discountAmt() {
        return discountCalculator.calculateDiscPrice();
    }
}
