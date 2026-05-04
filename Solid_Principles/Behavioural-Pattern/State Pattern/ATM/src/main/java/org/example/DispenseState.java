package org.example;

public class DispenseState implements ATMAction {

    @Override
    public void insertCard(ATMMachine machine) {
        System.out.println("Card already inserted. Please collect your cash first.");
    }

    @Override
    public void enterPin(ATMMachine machine) {
        System.out.println("Pin already verified. Please collect your cash.");
    }

    @Override
    public void withdrawCash(ATMMachine machine) {
        System.out.println("Cash dispensed. Please collect your cash.");
    }

    @Override
    public void ejectCard(ATMMachine machine) {
        System.out.println("Cash collected. Ejecting card. Thank you!");
        machine.setState(new IdleState());
    }
}
