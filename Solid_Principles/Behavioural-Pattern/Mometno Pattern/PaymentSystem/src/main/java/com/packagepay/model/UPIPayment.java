package com.packagepay.model;

public class UPIPayment implements Payment {
    public void pay() {
        System.out.println("UPI payment paid");
    }

    public String desc() {
      return "UPI";
    }
}
