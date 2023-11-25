public class Encapsulation {
    private double balance; // Приватное поле баланса счета

    // Метод для установки баланса счета
    public void setBalance(double amount) {
        if (amount >= 0) {
            balance = amount;
        } else {
            System.out.println("Invalid amount");
        }
    }

    // Метод для получения текущего баланса счета
    public double getBalance() {
        return balance;
    }

    // Метод для совершения пополнения счета
    public void deposit(double amount) {
        if (amount > 0) {
            balance += amount;
            System.out.println(amount + " deposited successfully");
        } else {
            System.out.println("Invalid deposit amount");
        }
    }

    // Метод для совершения снятия со счета
    public void withdraw(double amount) {
        if (amount > 0 && amount <= balance) {
            balance -= amount;
            System.out.println(amount + " withdrawn successfully");
        } else {
            System.out.println("Invalid withdrawal amount or insufficient balance");
        }
    }
}
