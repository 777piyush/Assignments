package com.hexaware.view;

import java.util.Scanner;

public class Bank {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        BankAccount<Double, String> account = null;

        while (true) {
            System.out.println("Choose an option:");
            System.out.println("1. Create Savings Account");
            System.out.println("2. Create Current Account");
            System.out.println("3. Exit");
            int choice = scanner.nextInt();

            switch (choice) {
                case 1:
                    account = createSavingsAccount(scanner);
                    break;
                case 2:
                    account = createCurrentAccount(scanner);
                    break;
                case 3:
                    System.out.println("Exiting...");
                    return;
                default:
                    System.out.println("Invalid choice");
                    break;
            }

            if (account != null) {
                operateAccount(scanner, account);
            }
        }
    }

    private static BankAccount<Double, String> createSavingsAccount(Scanner scanner) {
        System.out.println("Creating Savings Account");
        System.out.println("Enter account number:");
        double accountNumber = scanner.nextDouble();
        scanner.nextLine(); // Consume newline character
        System.out.println("Enter customer name:");
        String customerName = scanner.nextLine();
        System.out.println("Enter initial balance:");
        double balance = scanner.nextDouble();
        System.out.println("Enter interest rate:");
        double interestRate = scanner.nextDouble();

        return new SavingsAccount<>(accountNumber, customerName, balance, interestRate);
    }

    private static BankAccount<Double, String> createCurrentAccount(Scanner scanner) {
        System.out.println("Creating Current Account");
        System.out.println("Enter account number:");
        double accountNumber = scanner.nextDouble();
        scanner.nextLine(); // Consume newline character
        System.out.println("Enter customer name:");
        String customerName = scanner.nextLine();
        System.out.println("Enter initial balance:");
        double balance = scanner.nextDouble();

        return new CurrentAccount<>(accountNumber, customerName, balance);
    }

    private static void operateAccount(Scanner scanner, BankAccount<Double, String> account) {
        while (true) {
            System.out.println("Choose an operation:");
            System.out.println("1. Deposit");
            System.out.println("2. Withdraw");
            System.out.println("3. Calculate Interest");
            System.out.println("4. Back to Account Selection");
            int choice = scanner.nextInt();

            switch (choice) {
                case 1:
                    System.out.println("Enter deposit amount:");
                    double depositAmount = scanner.nextDouble();
                    account.deposit(depositAmount);
                    break;
                case 2:
                    System.out.println("Enter withdrawal amount:");
                    double withdrawAmount = scanner.nextDouble();
                    account.withdraw(withdrawAmount);
                    break;
                case 3:
                    if (account instanceof SavingsAccount) {
                        ((SavingsAccount<Double, String>) account).calculateInterest();
                    } else {
                        System.out.println("Interest calculation not applicable for Current Account.");
                    }
                    break;
                case 4:
                    return;
                default:
                    System.out.println("Invalid choice");
                    break;
            }
        }
    }
}
 