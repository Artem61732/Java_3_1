// Замена неправильного слова происходит, но в итоге почему-то
// изменения в файле так и не сохраняются




import javax.swing.*;
import java.io.*;
import java.util.Timer;
import java.util.TimerTask;

public class TextAutocorrect extends JFrame {

    private JTextArea textArea;
    private JFileChooser fileChooser;
    private Timer timer;

    public TextAutocorrect() {
        setTitle("Простой текстовый редактор");
        setSize(600, 400);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        textArea = new JTextArea();
        JScrollPane scrollPane = new JScrollPane(textArea);
        add(scrollPane);

        JMenuBar menuBar = new JMenuBar();
        JMenu fileMenu = new JMenu("Файл");
        JMenuItem openItem = new JMenuItem("Открыть");
        JMenuItem saveItem = new JMenuItem("Сохранить");

        openItem.addActionListener(e -> openFile());
        saveItem.addActionListener(e -> saveFile());

        fileMenu.add(openItem);
        fileMenu.add(saveItem);
        menuBar.add(fileMenu);
        setJMenuBar(menuBar);

        fileChooser = new JFileChooser();
        timer = new Timer();

        // Запуск таймера для проверки орфографии каждые 30 секунд
        timer.schedule(new AutoCorrectTask(), 0, 30 * 1000);

        setVisible(true);
    }

    // Метод для открытия файла
    private void openFile() {
        int returnValue = fileChooser.showOpenDialog(null);
        if (returnValue == JFileChooser.APPROVE_OPTION) {
            File selectedFile = fileChooser.getSelectedFile();
            try {
                BufferedReader reader = new BufferedReader(new InputStreamReader(new FileInputStream(selectedFile), "UTF-8"));
                textArea.read(reader, null);
                reader.close();
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        }
    }

    // Метод для сохранения файла
    private void saveFile() {
        int returnValue = fileChooser.showSaveDialog(null);
        if (returnValue == JFileChooser.APPROVE_OPTION) {
            File selectedFile = fileChooser.getSelectedFile();
            try {
                BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(selectedFile), "UTF-8"));
                textArea.write(writer);
                writer.close();
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        }
    }

    // Класс для выполнения автозамены в тексте
    private class AutoCorrectTask extends TimerTask {
        @Override
        public void run() {
            // Определяем, что искать и заменять
            String incorrectWord = "неправильное";
            String correctWord = "правильное";

            String currentText = textArea.getText();
            String correctedText = currentText.replace(incorrectWord, correctWord);

            if (!currentText.equals(correctedText)) {
                SwingUtilities.invokeLater(() -> {
                    textArea.setText(correctedText);
                });
            }
        }
    }

    public static void main(String[] args) {
        new TextAutocorrect();
    }
}
