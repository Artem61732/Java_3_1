import java.io.*;
import java.util.*;

public class CompressingTextFile {

    public static void main(String[] args) {
        String sourceFileName = "source.txt";
        String compressedFileName = "compressed.txt";
        String restoredFileName = "restored.txt";

        // Сжатие файла
        compressFile(sourceFileName, compressedFileName);

        // Восстановление из сжатого файла
        restoreFile(compressedFileName, restoredFileName);
    }

    // Метод для сжатия файла
    private static void compressFile(String sourceFileName, String compressedFileName) {
        try {
            BufferedReader reader = new BufferedReader(new FileReader(sourceFileName));
            PrintWriter writer = new PrintWriter(new FileWriter(compressedFileName));

            HashSet<String> uniqueLines = new HashSet<>();
            String line;

            int removedDuplicates = 0;

            while ((line = reader.readLine()) != null) {
                if (uniqueLines.contains(line)) {
                    removedDuplicates++;
                } else {
                    uniqueLines.add(line);
                    writer.println(line);
                }
            }

            writer.println("Количество удаленных дублирующихся строк: " + removedDuplicates);

            reader.close();
            writer.close();

            System.out.println("Файл успешно сжат.");
        } catch (IOException e) {
            System.out.println("Ошибка при сжатии файла: " + e.getMessage());
        }
    }

    // Метод для восстановления файла из сжатой версии
    private static void restoreFile(String compressedFileName, String restoredFileName) {
        try {
            BufferedReader reader = new BufferedReader(new FileReader(compressedFileName));
            PrintWriter writer = new PrintWriter(new FileWriter(restoredFileName));

            ArrayList<String> lines = new ArrayList<>();
            String line;

            while ((line = reader.readLine()) != null) {
                if (!line.startsWith("Количество удаленных дублирующихся строк:")) {
                    lines.add(line);
                }
            }

            for (String l : lines) {
                writer.println(l);
            }

            reader.close();
            writer.close();

            System.out.println("Файл восстановлен.");
        } catch (IOException e) {
            System.out.println("Ошибка при восстановлении файла: " + e.getMessage());
        }
    }
}
