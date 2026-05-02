package com.packagepay.model;

public class NetBanking implements Payment {
     public void pay() {
        System.out.println("NetBanking payment paid");
    }
    public String desc() {
      return "NetBanking";
    }
}
