package org.example;

public interface ATMAction {
    void insertCard(ATMMachine machine);
    void enterPin(ATMMachine machine);
    void withdrawCash(ATMMachine machine);
    void ejectCard(ATMMachine machine);
}
