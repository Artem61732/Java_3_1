import javax.swing.*;
import java.io.*;

public class TextEditor extends JFrame {

    private JTextArea textArea;
    private JFileChooser fileChooser;

    public TextEditor() {
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

        setVisible(true);
    }

    // Метод для открытия файла
    private void openFile() {
        int returnValue = fileChooser.showOpenDialog(null);
        if (returnValue == JFileChooser.APPROVE_OPTION) {
            File selectedFile = fileChooser.getSelectedFile();
            try {
                // Чтение файла с указанием кодировки UTF-8 для поддержки кириллицы
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
                // Сохранение файла с указанием кодировки UTF-8 для поддержки кириллицы
                BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(selectedFile), "UTF-8"));
                textArea.write(writer);
                writer.close();
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        }
    }

    public static void main(String[] args) {
        new TextEditor();
    }
}
