package views;

import controllers.Controller;
import model.Card;
import model.IGameView;
import mycomponents.MyButton;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.util.HashMap;

public class MainView extends JFrame implements IGameView {
    Controller controller;
    private HashMap<Integer, MyButton> buttons = new HashMap<>(); // для быстрого поиска кнопки

    public MainView()
    {
    }

    public void setController(Controller controller)
    {
        this.controller = controller;
    }

    public void initView()
    {
        initFrame(controller.getRow(), controller.getColumn());
    }

    private void initFrame(int row, int column) {
        //setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE); // чтобы выходило при закрытии
        setLayout(new GridBagLayout());
        createButtons(row, column);
        setTitle("Image Matching");
        setResizable(false); // чтобы размеры окна не изменялись
    }

    public void showView()
    {
        setVisible(true);
        pack(); // размеры по размеру панели
        setLocationRelativeTo(null); // чтобы окно было по центру
    }

    private void createButtons(int countRow, int countColumn)
    {
        MyButton button;

        GridBagConstraints c = new GridBagConstraints();
        c.fill = GridBagConstraints.HORIZONTAL;
        JFrame frame = this;

        for (int i = 0; i < countRow; i++) {
            for (int j = 0; j < countColumn; j++) {
                button = new MyButton(i, j);
                button.addActionListener(this::actionPerformed);
                c.gridx = j;
                c.gridy = i;
                add(button, c);
                addButton(button); // зачем контроллеру кнопки
            }
        }
    }

    public void actionPerformed(ActionEvent e) {
        MyButton button= (MyButton) e.getSource();
        controller.openCard(button.getRow(), button.getColumn());
    }

    public void changeStateCard(Card card)
    {
        MyButton button = buttons.get(card.getRow() * controller.getColumn() + card.getColumn());

        Image img = card.getDisplayImage();
        Image newImg = img.getScaledInstance(200, 200, Image.SCALE_SMOOTH);
        button.setIcon(new ImageIcon( newImg ));
    }

    public void addButton(MyButton button)
    {
        buttons.put(button.getRow() * controller.getColumn() + button.getColumn(), button);
    }

    @Override
    public void showMessage(String message) {
        JOptionPane.showMessageDialog(this, message);
    }
}
