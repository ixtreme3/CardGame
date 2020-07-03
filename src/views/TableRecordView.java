package views;

import controllers.Controller;
import model.Record;
import model.TypeDifficulty;
import service.FileManager;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Vector;
import java.util.stream.Collectors;

public class TableRecordView extends JFrame {
    private JTable table1;
    private JPanel panel1;

    public TableRecordView(Controller controller)
    {
        setDefaultCloseOperation(WindowConstants.HIDE_ON_CLOSE);
        setContentPane(panel1);
        setVisible(true);
        //setResizable(false); //размеры не изменялись
        //pack();
        setSize(400, 200);

        setLocationRelativeTo(null); //чтобы по центру

        DefaultTableModel tableModel = new DefaultTableModel();
        tableModel.addColumn("Имя пользователя");
        tableModel.addColumn("Кол-во ошибок");
        tableModel.addColumn("Уровень");

        table1.setModel(tableModel);

        tableModel.addRow(new Object[] {"Имя пользователя", "Кол-во ошибок", "Уровень"});

        List<Record> sortedRecords = controller.getSortedRecords();

        for (int i = 0; i < sortedRecords.size(); i++) {
            tableModel.addRow(new Object[] { sortedRecords.get(i).getLogin(),
                    sortedRecords.get(i).getCountError(), sortedRecords.get(i).getDifficultyString() });
        }
    }
}
