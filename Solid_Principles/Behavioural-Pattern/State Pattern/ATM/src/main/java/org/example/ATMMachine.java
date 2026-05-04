package org.example;

public class ATMMachine {

    private ATMAction state;

    public ATMMachine() {
        state = new IdleState();
    }

    public void setState(ATMAction newState) {
        this.state = newState;
    }

    public ATMAction getState() {
        return state;
    }

    public void insertCard() {
        state.insertCard(this);
    }

    public void enterPin() {
        state.enterPin(this);
    }

    public void withdrawCash() {
        state.withdrawCash(this);
    }

    public void ejectCard() {
        state.ejectCard(this);
    }
}
