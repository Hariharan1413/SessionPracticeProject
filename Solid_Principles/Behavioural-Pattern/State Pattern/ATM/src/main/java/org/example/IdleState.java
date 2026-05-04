package org.example;

public class IdleState implements ATMAction {

    @Override
    public void insertCard(ATMMachine machine) {
        System.out.println("Card inserted successfully.");
        machine.setState(new CardInsertState());
    }

    @Override
    public void enterPin(ATMMachine machine) {
        System.out.println("Insert card first, then enter pin.");
    }

    @Override
    public void withdrawCash(ATMMachine machine) {
        System.out.println("Insert card and enter pin first.");
    }

    @Override
    public void ejectCard(ATMMachine machine) {
        System.out.println("No card inserted. Insert card and complete transaction first.");
    }
}
