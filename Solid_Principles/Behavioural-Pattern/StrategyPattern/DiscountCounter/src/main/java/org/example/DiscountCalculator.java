package org.example;

public class DiscountCalculator {
    private String discPerc;
    private int productPrice;
    
    public DiscountCalculator(String discPerc, int productPrice) {
        this.discPerc = discPerc;
        this.productPrice = productPrice;
    }
    
    public double calculateDiscPrice() { 

        String perc = discPerc.replace("%", "").trim();
        double percentage;

        try {
            percentage = Double.parseDouble(perc);
        } catch (NumberFormatException ex) {
            throw new IllegalArgumentException("Invalid discount percentage: " + discPerc, ex);
        }

        if (percentage < 0 || percentage > 100) {
            throw new IllegalArgumentException("Discount percentage must be between 0 and 100");
        }

        double discountAmount = (productPrice * percentage) / 100.0;
        return productPrice - discountAmount;
    }
}
