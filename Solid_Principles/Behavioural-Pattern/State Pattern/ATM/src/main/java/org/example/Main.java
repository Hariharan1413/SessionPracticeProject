package org.example;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    public static void main(String[] args) {

        // Normal flow: Insert → Pin → Withdraw → Eject
        System.out.println("=== Normal ATM Flow ===");
        ATMMachine machine = new ATMMachine();
        machine.insertCard();
        machine.enterPin();
        machine.withdrawCash();
        machine.ejectCard();

        System.out.println();

        // Flow with early eject: Insert → Pin → Eject → Withdraw (should fail)
        System.out.println("=== Early Eject Flow ===");
        ATMMachine machine1 = new ATMMachine();
        machine1.insertCard();
        machine1.enterPin();
        machine1.ejectCard();
        machine1.withdrawCash(); // Should fail: no card inserted
    }
}