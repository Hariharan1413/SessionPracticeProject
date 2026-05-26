package org.example.factoryinterface;

import org.example.HdfcBank;
import org.example.ICICBank;

public class BankFactory {

    public static Bank Selectbank(String bankName) {
        if(bankName.equals("HDFC")) {
            return new HdfcBank();
        } else {
            return new ICICBank();
        }
    }
}
