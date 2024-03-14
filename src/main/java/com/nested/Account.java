package com.nested;

public class Account {
    private int balance = 0;
    private String owner;

    public Account(String owner) {
        this.owner = owner;
        System.out.println("\nAccount created for " + owner + " with initial balance of 0");
    }

    public void deposit(int amount) {
        if (amount < 0) throw new IllegalArgumentException("Amount cannot be negative");
        balance += amount;
        System.out.println(amount + " deposited. New balance: " + balance);
    }

    public void withdraw(int amount) {
        if (amount > balance) throw new InsufficientFundsException("Insufficient funds");
        balance -= amount;
        System.out.println(amount + " withdrawn. New balance: " + balance);
    }

    public int getBalance() {
        return balance;
    }
}
