package org.example;

import org.example.factoryinterface.Account;
import org.example.factoryinterface.Bank;
import org.example.factoryinterface.BankFactory;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    public static void main(String[] args) {

        Bank bank = BankFactory.Selectbank("HDFC");
        Account acount =  bank.getAccount();
        acount.openAccount();
    }
}