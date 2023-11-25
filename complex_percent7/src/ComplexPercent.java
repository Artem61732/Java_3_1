import java.util.Scanner;

public class ComplexPercent {
    public static void main(String[] args) {
        try (Scanner scanner = new Scanner(System.in)) {
            System.out.println("Выберите действие:");
            System.out.println("1. Вычислить сложный процент");
            System.out.println("2. Определить процент для достижения заданной суммы");

            int choice = scanner.nextInt();

            if (choice == 1) {
                System.out.println("Введите начальную сумму:");
                double principal = scanner.nextDouble();

                System.out.println("Введите годовую процентную ставку:");
                double interestRate = scanner.nextDouble() / 100; // Процентная ставка в десятичном виде

                System.out.println("Введите количество периодов (лет):");
                int years = scanner.nextInt();

                double amount = calculateCompoundInterest(principal, interestRate, years);
                System.out.println("Сумма после " + years + " лет составит: " + amount);
            } else if (choice == 2) {
                System.out.println("Введите начальную сумму:");
                double principal = scanner.nextDouble();

                System.out.println("Введите желаемую конечную сумму:");
                double targetAmount = scanner.nextDouble();

                System.out.println("Введите количество периодов (лет):");
                int years = scanner.nextInt();

                double requiredInterestRate = calculateRequiredInterestRate(principal, targetAmount, years);
                System.out.println("Необходимая процентная ставка составит: " + (requiredInterestRate * 100) + "%");
            } else {
                System.out.println("Неверный выбор.");
            }
        }
    }

    // Метод для вычисления сложного процента
    private static double calculateCompoundInterest(double principal, double interestRate, int years) {
        return principal * Math.pow(1 + interestRate, years);
    }

    // Метод для определения необходимого процента для достижения заданной суммы за период времени
    private static double calculateRequiredInterestRate(double principal, double targetAmount, int years) {
        return Math.pow((targetAmount / principal), 1.0 / years) - 1;
    }
}
