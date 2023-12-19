package com.hexaware.view;

abstract class BankAccount<T extends Number, U> {
    private T accountNumber;
    private U customerName;
    private T balance;

    // Default constructor
    public BankAccount() {
        this.accountNumber = null;
        this.customerName = null;
        this.balance = null;
    }

    // Constructor with account number and customer name
    public BankAccount(T accountNumber, U customerName, T balance) {
        this.accountNumber = accountNumber;
        this.customerName = customerName;
        this.balance = balance; // Balance initialized as null
    }

    // Getters and setters
    public T getAccountNumber() {
        return accountNumber;
    }

    public void setAccountNumber(T accountNumber) {
        this.accountNumber = accountNumber;
    }

    public U getCustomerName() {
        return customerName;
    }

    public void setCustomerName(U customerName) {
        this.customerName = customerName;
    }

    public T getBalance() {
        return balance;
    }

    public void setBalance(T balance) {
        this.balance = balance;
    }

    // Print all information
    public void printAllInfo() {
        System.out.println("Account Number: " + accountNumber);
        System.out.println("Customer Name: " + customerName);
        System.out.println("Balance: " + balance);
    }

    // Abstract methods
    public abstract void deposit(T amount);

    public abstract void withdraw(T amount);

    public abstract void calculateInterest();
}

// task 9.2


class SavingsAccount<T extends Number, U> extends BankAccount<T, U> {
    private double interestRate;

    public SavingsAccount(T accountNumber, U customerName, T balance, double interestRate) {
        super(accountNumber, customerName, balance);
        this.interestRate = interestRate;
    }

    public double getInterestRate() {
        return interestRate;
    }

    public void setInterestRate(double interestRate) {
        this.interestRate = interestRate;
    }

    @Override
    public void calculateInterest() {
        double interest = getBalance().doubleValue() * interestRate / 100;
        setBalance((T) (Double.valueOf(getBalance().doubleValue() + interest)));
        System.out.println("Interest calculated. New balance: " + getBalance());
    }

    @Override
    public void deposit(T amount) {
        setBalance((T) (Double.valueOf(getBalance().doubleValue() + amount.doubleValue())));
        System.out.println("Deposited successfully. New balance: " + getBalance());
    }

    @Override
    public void withdraw(T amount) {
        if (amount.doubleValue() <= getBalance().doubleValue()) {
            setBalance((T) (Double.valueOf(getBalance().doubleValue() - amount.doubleValue())));
            System.out.println("Withdrawn successfully. New balance: " + getBalance());
        } else {
            System.out.println("Not sufficient funds for withdrawal.");
        }
    }
}

class CurrentAccount<T extends Number, U> extends BankAccount<T, U> {
    private static final double OVERDRAFT_LIMIT = 1000.0;

    public CurrentAccount(T accountNumber, U customerName, T balance) {
        super(accountNumber, customerName, balance);
    }

    @Override
    public void withdraw(T amount) {
        double currentBalance = getBalance().doubleValue();
        if (amount.doubleValue() <= currentBalance + OVERDRAFT_LIMIT) {
            setBalance((T) (Double.valueOf(currentBalance - amount.doubleValue())));
            System.out.println("Withdrawn successfully. New balance: " + getBalance());
        } else {
            System.out.println("Not sufficient funds for withdrawal.");
        }
    }

    @Override
    public void calculateInterest() {
        System.out.println("No interest for current account.");
    }

    @Override
    public void deposit(T amount) {
        setBalance((T) (Double.valueOf(getBalance().doubleValue() + amount.doubleValue())));
        System.out.println("Deposited successfully. New balance: " + getBalance());
    }
}

