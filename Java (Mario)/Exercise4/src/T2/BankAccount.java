package T2;

public class BankAccount {
    private double balance;
    private int accountNumber;
    private String owner;
    private static int count = 0;

    public BankAccount(int accountNumber, String owner) {
        this.accountNumber = accountNumber;
        this.owner = owner;
        balance = 0;
        count++;
    }

    public static int getCount() {
        return count;
    }

    public void deposit(double amount) {
        balance += amount;
    }

    public boolean withdraw(double amount) {
        if (balance >= amount) {
            balance -= amount;
            return true;
        }
        return false;
    }

    public boolean transfer(BankAccount ba, double amount) {
        if (this.withdraw(amount)) {
            ba.deposit(amount);
            return true;
        }
        return false;
    }

    public void show() {
        System.out.printf("Account Number: %d, Owner's Name: %s, Balance: %f\n", accountNumber, owner, balance);
    }

    public double getBalance() {
        return balance;
    }

    public void setBalance(double balance) {
        this.balance = balance;
    }

    public int getAccountNumber() {
        return accountNumber;
    }

    public void setAccountNumber(int accountNumber) {
        this.accountNumber = accountNumber;
    }

    public String getOwner() {
        return owner;
    }

    public void setOwner(String owner) {
        this.owner = owner;
    }

}
