import java.util.Scanner;

public class LeapYear {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        boolean validInput = false;
        int year = 0;

        while (!validInput) {
            try {
                System.out.println("Введите год для проверки (например, 2023):");
                year = Integer.parseInt(scanner.nextLine());

                if (isLeapYear(year)) {
                    System.out.println(year + " - високосный год.");
                } else {
                    System.out.println(year + " - не високосный год.");
                }
                validInput = true;
            } catch (NumberFormatException e) {
                System.out.println("Ошибка: Введите целое число.");
            }
        }

        scanner.close();
    }

    // Метод для проверки, является ли год високосным
    private static boolean isLeapYear(int year) {
        return (year % 4 == 0) && (year % 100 != 0 || year % 400 == 0);
    }
}
