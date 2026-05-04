package org.example;

public class ProcessingState implements ATMAction {

    @Override
    public void insertCard(ATMMachine machine) {
        System.out.println("Card already inserted.");
    }

    @Override
    public void enterPin(ATMMachine machine) {
        System.out.println("Pin already verified. Please select withdrawal amount.");
    }

    @Override
    public void withdrawCash(ATMMachine machine) {
        System.out.println("Please enter the withdrawal amount.");
        machine.setState(new DispenseState());
    }

    @Override
    public void ejectCard(ATMMachine machine) {
        System.out.println("Card ejected. Transaction cancelled.");
        machine.setState(new IdleState());
    }
}
