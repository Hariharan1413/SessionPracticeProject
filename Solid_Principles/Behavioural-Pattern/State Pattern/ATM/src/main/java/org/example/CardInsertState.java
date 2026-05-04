package org.example;

public class CardInsertState implements ATMAction {

    @Override
    public void insertCard(ATMMachine machine) {
        System.out.println("Card already inserted. Please enter your pin.");
    }

    @Override
    public void enterPin(ATMMachine machine) {
        System.out.println("Pin entered. Processing...");
        machine.setState(new ProcessingState());
    }

    @Override
    public void withdrawCash(ATMMachine machine) {
        System.out.println("Please enter pin first.");
    }

    @Override
    public void ejectCard(ATMMachine machine) {
        System.out.println("Card ejected. Transaction cancelled.");
        machine.setState(new IdleState());
    }
}
