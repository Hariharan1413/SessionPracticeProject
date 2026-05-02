package org.example;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);

        System.out.println("Enter product price:");
        int productPrice = scanner.nextInt();

        System.out.println("Enter discount type (1 = Festival, 2 = Premium User):");
        int choice = scanner.nextInt();

        // Dynamically select discount strategy based on input
        OrderDiscount discount;
        switch (choice) {
            case 1:
                discount = new FestivalDiscount(productPrice, "15%");
                break;
            case 2:
                discount = new PremiumUserDiscount(productPrice, "25%");
                break;
            default:
                System.out.println("Invalid choice. No discount applied.");
                return;
        }

        // Context uses the selected strategy
        DiscountService discountService = new DiscountService(discount);
        double finalPrice = discountService.productDiscAmt();
        System.out.println("Final price after discount: " + finalPrice);

        // Demonstrate runtime strategy switching (no new DiscountService needed)
        System.out.println("\n--- Switching strategy at runtime ---");
        discountService.setDiscountStrategy(new FestivalDiscount(productPrice, "10%"));
        double festivalPrice = discountService.productDiscAmt();
        System.out.println("Final price after Festival 10% discount: " + festivalPrice);

        scanner.close();
    }
}