import javax.swing.*;
import java.awt.*;

public class Layout extends JFrame {

    public Layout() {
        setTitle("Пример менеджеров компоновки");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(700, 500);

        // Используем BorderLayout для основного контейнера (JFrame)
        setLayout(new BorderLayout());

        // Панель вверху
        JPanel topPanel = new JPanel();
        topPanel.setLayout(new FlowLayout());
        topPanel.setBackground(Color.PINK);
        topPanel.add(new JButton("1"));
        topPanel.add(new JButton("2"));
        topPanel.add(new JButton("3"));
        add(topPanel, BorderLayout.NORTH);

        // Панель в центре
        JPanel centerPanel = new JPanel();
        centerPanel.setLayout(new BorderLayout());

        centerPanel.add(new JButton("центр"), BorderLayout.CENTER);
        centerPanel.add(new JButton("север"), BorderLayout.NORTH);
        centerPanel.add(new JButton("юг"), BorderLayout.SOUTH);
        centerPanel.add(new JButton("запад"), BorderLayout.WEST);
        centerPanel.add(new JButton("восток"), BorderLayout.EAST);

        add(centerPanel, BorderLayout.CENTER);

        setVisible(true);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(Layout::new);
    }
}
