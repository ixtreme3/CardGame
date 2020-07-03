package service;

import model.Record;
import model.TypeDifficulty;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class FileManager {
    public static void saveResult(int countError, TypeDifficulty typeDifficulty)
    {
        try (BufferedWriter bw = new BufferedWriter(new FileWriter("table.txt", true)))
        {
            bw.write(ApplicationHelper.getInstance().getLogin() + " " + countError + " " + typeDifficulty.ordinal() + "\n");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static List<Record> readResults()
    {
        ArrayList<Record> records = new ArrayList<>();

        try(BufferedReader br = new BufferedReader(new FileReader("table.txt")))
        {
            String s;
            while ((s = br.readLine()) != null)
            {
                String[] a = s.split(" ");
                Record record = new Record(a[0], Integer.parseInt(a[1]), TypeDifficulty.values()[Integer.parseInt(a[2])]);
                records.add(record);
            }
        } catch (FileNotFoundException e) {

        } catch (IOException e) {
            e.printStackTrace();
        }
        return records;
    }
}
