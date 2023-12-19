package com.hexaware.entity;

public class Account {
    private static int lastAccNo = 1000; // Starting account number
    private int accountNumber;
    private String accountType;
    private double accountBalance;
    private Customer customer;

    // Constructor
    public Account(String accountType, double accountBalance, Customer customer) {
        this.accountType = accountType;
        this.accountBalance = accountBalance;
        this.customer = customer;
        this.accountNumber = ++lastAccNo; // Generating a unique account number
    }

    public Account(int accountId, int customerId, String accountNumber2, String accountType2, double accountBalance2) {
		// TODO Auto-generated constructor stub
	}

	public double calculateInterest() {
		// TODO Auto-generated method stub
		return 0;
	}

	// Getters and Setters
    public int getAccountNumber() {
        return accountNumber;
    }

    public String getAccountType() {
        return accountType;
    }

    public void setAccountType(String accountType) {
        this.accountType = accountType;
    }

    public double getAccountBalance() {
        return accountBalance;
    }

    public void setAccountBalance(double accountBalance) {
        this.accountBalance = accountBalance;
    }

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    // Method to deposit funds
    public void deposit(double amount) {
        accountBalance += amount;
        System.out.println(amount + " deposited successfully. Current balance: " + accountBalance);
    }

    // Method to withdraw funds
    public void withdraw(double amount) {
        if (amount <= accountBalance) {
            accountBalance -= amount;
            System.out.println(amount + " withdrawn successfully. Current balance: " + accountBalance);
        } else {
            System.out.println("Insufficient funds!");
        }
    }
}

class SavingsAccount extends Account {
	   private double interestRate;
	   private static final double MINIMUM_BALANCE = 500.0;
	   public SavingsAccount(int accountId, int customerId, String accountNumber, String accountType, double accountBalance, double interestRate) {
	       super(accountId, customerId, accountNumber, accountType, (accountBalance >= MINIMUM_BALANCE ? accountBalance : 0.0));
	       this.interestRate = interestRate;
	       if (accountBalance >= MINIMUM_BALANCE) {
	           this.interestRate = interestRate;
	       } else {
	           System.out.println("Initial balance must be at least " + MINIMUM_BALANCE + " to open a savings account.");
	       }
	   }
	   @Override
	   public double calculateInterest() {
	       return (getAccountBalance() * interestRate) / 100;
	   }
	}
	class CurrentAccount extends Account {
	   private static final double OVERDRAFT_LIMIT = 1000.0; // Constant for overdraft limit
	   public CurrentAccount(int accountId, int customerId, String accountNumber, String accountType, double accountBalance) {
	       super(accountId, customerId, accountNumber, accountType, accountBalance);
	   }
	   @Override
	   public void withdraw(double amount) {
	       if (amount <= (getAccountBalance() + OVERDRAFT_LIMIT)) {
	           setAccountBalance(getAccountBalance() - amount);
	           System.out.println("Withdrawn: " + amount);
	       } else {
	           System.out.println("Insufficient balance for withdrawal beyond overdraft limit.");
	       }
	   }
	  
	class ZeroBalanceAccount extends Account {
	   public ZeroBalanceAccount(int accountId, int customerId, String accountNumber, String accountType) {
	       super(accountId, customerId, accountNumber, accountType, 0.0);
	   }
	}
	}


