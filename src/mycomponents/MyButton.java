package mycomponents;

import javax.swing.*;

public class MyButton extends JButton {
    private int row, column;

    public MyButton(int row, int column) {
        this.row = row;
        this.column = column;
    }

    public int getRow() {
        return row;
    }

    public int getColumn() {
        return column;
    }
}
