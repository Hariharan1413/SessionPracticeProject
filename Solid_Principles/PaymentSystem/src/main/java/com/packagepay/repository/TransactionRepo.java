package com.packagepay.repository;

public class TransactionRepo {
    
   public void savePay(int amt,String desc) {
        System.out.println("Saved successfully " + amt + " " + desc);
    }
}
