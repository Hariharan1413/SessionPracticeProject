package org.example.services;

public class User {
    private String name;
    private int creditScore;
    private double income;

    public User(String name,int creditScore, double income) {
        this.name = name;
        this.creditScore = creditScore;
        this.income = income;
    }

    public String getName(){
        return name;
    }

    public int getCreditScore() {
        return creditScore;
    }

    public double getIncome(){
        return income;
    }
}
