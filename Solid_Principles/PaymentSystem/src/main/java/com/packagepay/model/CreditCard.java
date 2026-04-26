package com.packagepay.model;

public class CreditCard implements Payment {
    public void pay() {
        System.out.println("CreditCard payment paid");
    }
        public String desc() {
      return "CreditCard";
    }
}
