import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class CalculatorGUI extends JFrame implements ActionListener {
    private JTextField textField;
    private JButton[] numberButtons = new JButton[10];
    private JButton[] functionButtons = new JButton[4];
    private JButton addButton, subButton, mulButton, divButton;
    private JButton decimalButton, equalButton, clearButton;
    private JPanel panel;

    private double num1 = 0, num2 = 0, result = 0;
    private char operator;

    public CalculatorGUI() {
        setTitle("Калькулятор");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(400, 400);
        setResizable(false);

        panel = new JPanel();
        panel.setLayout(new GridLayout(5, 4, 10, 10));

        textField = new JTextField();
        textField.setEditable(false);

        addButton = new JButton("+");
        subButton = new JButton("-");
        mulButton = new JButton("*");
        divButton = new JButton("/");

        functionButtons[0] = addButton;
        functionButtons[1] = subButton;
        functionButtons[2] = mulButton;
        functionButtons[3] = divButton;

        decimalButton = new JButton(".");
        equalButton = new JButton("=");
        clearButton = new JButton("C");

        numberButtons[0] = new JButton("0");
        for (int i = 1; i < 10; i++) {
            numberButtons[i] = new JButton(String.valueOf(i));
        }

        panel.add(clearButton);
        for (int i = 0; i < 10; i++) {
            panel.add(numberButtons[i]);
        }
        panel.add(decimalButton);
        panel.add(equalButton);
        for (int i = 0; i < 4; i++) {
            panel.add(functionButtons[i]);
        }

        add(textField, BorderLayout.NORTH);
        add(panel);

        for (int i = 0; i < 10; i++) {
            numberButtons[i].addActionListener(this);
        }
        for (int i = 0; i < 4; i++) {
            functionButtons[i].addActionListener(this);
        }

        decimalButton.addActionListener(this);
        equalButton.addActionListener(this);
        clearButton.addActionListener(this);

        setVisible(true);
    }

    public void actionPerformed(ActionEvent e) {
        for (int i = 0; i < 10; i++) {
            if (e.getSource() == numberButtons[i]) {
                textField.setText(textField.getText().concat(String.valueOf(i)));
            }
        }
        if (e.getSource() == decimalButton) {
            textField.setText(textField.getText().concat("."));
        }
        if (e.getSource() == clearButton) {
            textField.setText("");
        }
        if (e.getSource() == addButton) {
            num1 = Double.parseDouble(textField.getText());
            operator = '+';
            textField.setText("");
        }
        if (e.getSource() == subButton) {
            num1 = Double.parseDouble(textField.getText());
            operator = '-';
            textField.setText("");
        }
        if (e.getSource() == mulButton) {
            num1 = Double.parseDouble(textField.getText());
            operator = '*';
            textField.setText("");
        }
        if (e.getSource() == divButton) {
            num1 = Double.parseDouble(textField.getText());
            operator = '/';
            textField.setText("");
        }
        if (e.getSource() == equalButton) {
            num2 = Double.parseDouble(textField.getText());
            switch (operator) {
                case '+':
                    result = num1 + num2;
                    break;
                case '-':
                    result = num1 - num2;
                    break;
                case '*':
                    result = num1 * num2;
                    break;
                case '/':
                    if (num2 == 0) {
                        textField.setText("Ошибка: деление на ноль");
                    } else {
                        result = num1 / num2;
                    }
                    break;
            }
            textField.setText(String.valueOf(result));
            num1 = result;
        }
    }

    public static void main(String[] args) {
        new CalculatorGUI();
    }
}
