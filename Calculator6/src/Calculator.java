import java.util.Scanner;

public class Calculator {
    public static void main(String[] args) {
        try (Scanner scanner = new Scanner(System.in)) {
            // Запрос ввода пользователем выражения
            System.out.println("Введите выражение (например, 2 * 6.5):");
            String input = scanner.nextLine();

            try {
                // Вычисление результата выражения и его вывод
                double result = evaluateExpression(input);
                System.out.println("Результат: " + result);
            } catch (IllegalArgumentException e) {
                // Обработка ошибок и вывод сообщений об ошибке
                System.out.println("Ошибка: " + e.getMessage());
            }
        }
    }

    // Метод для вычисления результата арифметического выражения
    private static double evaluateExpression(String expression) {
        // Разделение входной строки на токены (число, оператор, число)
        String[] tokens = expression.split(" ");

        // Проверка на корректное количество токенов (должно быть три: число, оператор, число)
        if (tokens.length != 3) {
            throw new IllegalArgumentException("Неверный формат выражения.");
        }

        // Парсинг чисел и оператора из токенов
        double operand1 = Double.parseDouble(tokens[0]);
        String operator = tokens[1];
        double operand2 = Double.parseDouble(tokens[2]);

        // Вычисление результата в зависимости от оператора
        switch (operator) {
            case "+":
                return operand1 + operand2;
            case "-":
                return operand1 - operand2;
            case "*":
                return operand1 * operand2;
            case "/":
                // Проверка деления на ноль
                if (operand2 == 0) {
                    throw new IllegalArgumentException("Деление на ноль.");
                }
                return operand1 / operand2;
            default:
                throw new IllegalArgumentException("Неверный оператор.");
        }
    }
}
