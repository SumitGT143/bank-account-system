/**
 * Bank Account System
 * Demonstrates: Encapsulation, Inheritance, Polymorphism, Abstraction
 * Author: Sumit
 * Week 1 — Java OOP
 */

import java.util.*;
class BankAccount{
     private String holderName;
     private String accountNumber;
     private double balance;
    

    BankAccount(String holderName,String accountNumber, double balance){
        this.holderName = holderName;
        this.accountNumber = accountNumber;
        this.balance = balance;
    }
    

 void deposit(double amount){
    if(amount<= 0){
        System.out.println("Deposit amount must be poistive.");
        return;
    }
    balance += amount;
    System.out.println("Deposited: Rs." +amount + " | Balance: Rs" + balance);
 }

    void withdraw(double amount){
        if(amount <=0){
            System.out.println("Withdraw amount must be positive.");
            return;
        }
        if (amount > balance){
            System.out.println("Insufficient funds.");
            return;
        }
        balance -=amount;
        System.out.println("Withdrawn: Rs." + amount + " | Balance: Rs." + balance );
       
    }
    
    double getBalance() {  
        return balance;
    }
    

    void display(){
        System.out.println("Account Holder Name: " + holderName);
        System.out.println("Account number: " + accountNumber);
        System.out.println("Balance: " + balance);
        System.out.println();
    }
   
    String getHolderName() { return holderName;}
    String getAccountNumber(){ return accountNumber;}
    protected void setBalance(double balance) { this.balance = balance;}
    }
// ~~~~~~~~~~~~~~~~~~~~~~~~~
    class SavingAccount extends BankAccount{
        private double interestRate;
         SavingAccount(String holderName, String accountNumber, double balance, double interestRate){
            super(holderName, accountNumber, balance);
            this.interestRate = interestRate;

         }
         void applyInterest(){
            double interest = getBalance() * interestRate /100;
            deposit(interest);
         }
         }
// ~~~~~~~~~~~~~~~~~~~  
class CurrentAccount extends BankAccount {
    private double overdraftLimit;

    CurrentAccount(String holderName, String accountNumber,
                   double balance, double overdraftLimit) {
        super(holderName, accountNumber, balance);
        this.overdraftLimit = overdraftLimit;
    }

    @Override
    void withdraw(double amount) {
        if (amount <= 0) {
            System.out.println("Withdraw amount must be positive.");
            return;
        }
        if (amount > getBalance() + overdraftLimit) {
            System.out.println("Exceeds overdraft limit of Rs." + overdraftLimit);
            return;
        }
        setBalance(getBalance() - amount);
        System.out.println("Withdrawn: Rs." + amount + " | Balance: Rs." + getBalance());
    }

    @Override
    void display() {
        super.display();
        System.out.println("Overdraft Limit: Rs." + overdraftLimit);
    }
    
}

class AccountManager {
    private ArrayList<BankAccount> accounts;

    AccountManager() {
        accounts = new ArrayList<>();
    }

    // Add account
    void addAccount(BankAccount account) {
        accounts.add(account);
    }

    // Search account by account number
    BankAccount findAccount(String accNo) {
        for (BankAccount acc : accounts) {
            if (acc.getAccountNumber().equals(accNo)) {
                return acc;
            }
        }
        return null;
    }

    // Print all accounts
    void printAllAccounts() {
        System.out.println("\n===== ALL ACCOUNTS =====");
        for (BankAccount acc : accounts) {
            acc.display();   // Polymorphism in action
        }
    }

    // Total balance of bank
    double getTotalBalance() {
        double total = 0;
        for (BankAccount acc : accounts) {
            total += acc.getBalance();
        }
        return total;
    }
    
}
        

public class Main{
     public static void main(String[] args) {

        AccountManager bank = new AccountManager();

        SavingAccount s1 = new SavingAccount("Sumit", "SA101", 5000, 5);
        CurrentAccount c1 = new CurrentAccount("Rahul", "CA101", 10000, 5000);

        bank.addAccount(s1);
        bank.addAccount(c1);

        // Test savings
        s1.deposit(2000);
        s1.withdraw(500);
        s1.applyInterest();

        // Test current with overdraft
        c1.withdraw(14000); // should work — within overdraft
        c1.withdraw(2000);  // should block — exceeds limit

        // Print all
        bank.printAllAccounts();

        System.out.println("Total Bank Balance: Rs." + bank.getTotalBalance());
    }
}

    