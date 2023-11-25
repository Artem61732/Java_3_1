public class MainEncapsulation {
    public static void main(String[] args) {
        Encapsulation account = new Encapsulation();

        // Установка баланса счета с помощью метода setBalance()
        account.setBalance(1000);

        // Получение текущего баланса счета с помощью метода getBalance()
        double currentBalance = account.getBalance();
        System.out.println("Current Balance: " + currentBalance);

        // Попытка снять деньги со счета
        account.withdraw(500);

        // Попытка пополнить счет
        account.deposit(200);

        // Получение обновленного баланса счета
        currentBalance = account.getBalance();
        System.out.println("Updated Balance: " + currentBalance);
    }
}
