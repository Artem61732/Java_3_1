public class BubbleArray {
    public static void main(String[] args) {
        String[] strings = {"apple", "banana", "orange", "grape", "cherry"};

        System.out.println("Исходный массив строк:");
        printArray(strings);

        bubbleSort(strings);

        System.out.println("Отсортированный массив строк:");
        printArray(strings);
    }

    // Метод для сортировки массива строк методом пузырька
    private static void bubbleSort(String[] arr) {
        int n = arr.length;
        for (int i = 0; i < n - 1; i++) {
            for (int j = 0; j < n - i - 1; j++) {
                if (arr[j].compareTo(arr[j + 1]) > 0) {
                    // Обмен строками, если текущая строка больше следующей по алфавиту
                    String temp = arr[j];
                    arr[j] = arr[j + 1];
                    arr[j + 1] = temp;
                }
            }
        }
    }

    // Метод для вывода массива строк на экран
    private static void printArray(String[] arr) {
        for (String str : arr) {
            System.out.print(str + " ");
        }
        System.out.println();
    }
}
