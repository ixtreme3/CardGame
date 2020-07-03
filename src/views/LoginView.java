package views;
import controllers.Controller;
import model.ILoginView;
import model.TypeDifficulty;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class LoginView extends JFrame implements ILoginView {
    private JComboBox comboBox; // выпадающий список
    private JButton startButton;
    private JPanel panel;
    private JTextField textFieldLogin;
    private JButton tableRecordButton;
    private Controller controller;

    public LoginView()
    {
        tableRecordButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new TableRecordView(controller);
            }
        });
    }

    public JComboBox getComboBox()
    {
        return comboBox;
    }

    public JTextField getTextFieldLogin()
    {
        return textFieldLogin;
    }

    public void setController(Controller controller)
    {
        this.controller = controller;
    }

    public void showView()
    {
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setContentPane(panel); // добавляет во фрейм панель
        setVisible(true);
        setResizable(false); // чтобы размеры не изменялись
        setSize(400, 200);
        comboBox.addItem("Легкий");
        comboBox.addItem("Средний");
        comboBox.addItem("Сложный");
        setLocationRelativeTo(null); //чтобы по центру

        startButton.addActionListener(controller::actionEnterPerformed);
    }

    @Override
    public String getLogin() {
        return getTextFieldLogin().getText();
    }

    @Override
    public TypeDifficulty getTypeDifficulty() {
        int index = getComboBox().getSelectedIndex();
        return TypeDifficulty.getValue(index);
    }
}
