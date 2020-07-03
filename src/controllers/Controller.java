package controllers;

import model.*;
import mycomponents.MyButton;
import service.ApplicationHelper;
import service.FileManager;
import views.*;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.util.*;
import java.util.List;
import java.util.Timer;
import java.util.stream.Collectors;


public class Controller
{
    private Game game;
    private IGameView mainView;
    private ILoginView loginView;
    private Timer mTimer;
    TypeDifficulty typeDifficulty;

    public Controller(ILoginView loginView)
    {
        this.loginView = loginView;
        mTimer = new Timer();
    }

    private Image getImage(String name)
    {
        String filename = "/img/" + name.toLowerCase() + ".jpg";
        return new ImageIcon(getClass().getResource(filename)).getImage();
    }

    private void createGame()
    {
        Image[] images = new Image[10];

        for (int i = 0; i < images.length; i++) {
            images[i] = getImage(String.valueOf(i+1));
        }

        int row, column;

        if (typeDifficulty == TypeDifficulty.Easy)
        {
            row = 2;
            column = 4;
        }
        else if (typeDifficulty == TypeDifficulty.Medium)
        {
            row = 3;
            column = 4;
        }
        else
        {
            row = 4;
            column = 5;
        }

        game = new Game(images, row, column);
        game.addListener(mainView::changeStateCard);

        Card.setFrontImage(getImage("front"));
        Card.setDeleteImage(getImage("deleted"));
    }

    public void openCard(int row, int column) {
        game.openCard(row, column);

        mTimer.schedule(new TimerTask() {
            @Override
            public void run() {
                Status status = game.updateCards();
                if (status == Status.Win)
                {
                    mainView.showMessage("Вы выиграли!");
                    FileManager.saveResult(game.getCountError(), typeDifficulty);
                }
                else if (status == Status.Lose)
                    mainView.showMessage("Вы проиграли.");

                if (status != Status.None)
                {
                    game.initField();
                }
            }
        }, 1000);
    }

    public void actionEnterPerformed(ActionEvent e) { // нажатие на кнопку СТАРТ
        this.typeDifficulty = loginView.getTypeDifficulty();
        ApplicationHelper.getInstance().setLogin(loginView.getLogin());
        mainView = new MainView();
        mainView.setController(this);
        createGame();
        mainView.initView();
        game.initField();
        mainView.showView();
    }

    public int getRow() {
        return game.getHeight();
    }

    public int getColumn() {
        return game.getWidth();
    }

    public List<Record> getSortedRecords()
    {
        List<Record> records = FileManager.readResults();

        List<Record> sortedRecords = records.stream().sorted(new Comparator<Record>() {
            @Override
            public int compare(Record o1, Record o2) {
                if (o1.getTypeDifficulty().ordinal() < o2.getTypeDifficulty().ordinal())
                    return 1;
                else if (o1.getTypeDifficulty().ordinal() > o2.getTypeDifficulty().ordinal())
                    return -1;
                else
                {
                    if (o1.getCountError() > o2.getCountError())
                        return 1;
                    else if (o1.getCountError() < o2.getCountError())
                        return -1;
                }
                return 0;
            }
        }).collect(Collectors.toList());

        return sortedRecords;
    }
}
