package org.example;

// Concrete Strategy - Festival Discount
public class FestivalDiscount implements OrderDiscount {
    private final DiscountCalculator discCalc;

    public FestivalDiscount(int productPrice, String festivalDisc) {
        discCalc = new DiscountCalculator(festivalDisc, productPrice);
    }

    @Override
    public double discountAmt() {
        return discCalc.calculateDiscPrice();
    }
}
