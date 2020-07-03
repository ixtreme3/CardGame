package model;

import java.awt.*;
import java.util.ArrayList;
import java.util.Random;

public class Game {
    private Card[][] cards;
    private Card openCardFirst;
    private Card openCardSecond;
    private int countOpenCard = 0;
    private int countError = 0;
    private int maxCountError;
    Image[] images;
    private Random random = new Random();
    int width, height;
    private ArrayList<GameListener> listeners = new ArrayList<>();

    public Game(Image[] images, int height, int width)
    {
        if (width * height / 2 > images.length)
            throw new IllegalArgumentException("Недостаточно картинок");
        this.images = images;
        this.width = width;
        this.height = height;
        maxCountError = (int)(0.4*(width * height));
        //initField();
    }

    public void addListener(GameListener listener)
    {
        listeners.add(listener);
    }

    private void changeState(Card card)
    {
        for (GameListener listener:listeners) {
            listener.changeStateCard(card);
        }
    }

    public int getCountError() {
        return countError;
    }

    public void initField()
    {
        cards = new Card[height][width];

        for (int i = 0; i < height * width / 2; i++) {
            createCard(images[i]);
            createCard(images[i]);
        }

        openCardFirst = null;
        openCardSecond = null;
        countError = 0;
        countOpenCard = 0;
    }

    private void createCard(Image image)
    {
        Point p = getRandPosition();
        Card card1 = new Card(p.y, p.x, image);
        cards[p.y][p.x] = card1;
        changeState(card1);
    }

    private Point getRandPosition()
    {
        int row;
        int column;

        while (true)
        {
            row = random.nextInt(cards.length);
            column = random.nextInt(cards[0].length);

            if (cards[row][column] == null)
                break;
        }

        Point p = new Point(column, row);
        return p;
    }

    public Card getCard(int row, int column)
    {
        return cards[row][column];
    }

    public int getWidth()
    {
        return width;
    }

    public int getHeight()
    {
        return height;
    }

    public void openCard(int row, int column)
    {
        Card card = cards[row][column];
        if (card.isDeleted() || card.isOpen())
            return;

        if (openCardFirst == null)
            openCardFirst = card;
        else
            openCardSecond = card;

        card.setOpen(true);
        changeState(card);
    }

    public Status updateCards()
    {
        if (openCardFirst != null && openCardSecond != null)
        {
            if (openCardFirst.getDisplayImage() == openCardSecond.getDisplayImage())
            {
                openCardFirst.setDeleted(true);
                openCardSecond.setDeleted(true);

                countOpenCard += 2;

                if (countOpenCard == getHeight() * getWidth())
                    return Status.Win;
            }
            else
            {
                openCardFirst.setOpen(false);
                openCardSecond.setOpen(false);

                countError++;

                if (countError > maxCountError)
                    return Status.Lose;
            }

            changeState(openCardFirst);
            changeState(openCardSecond);

            openCardFirst = null;
            openCardSecond = null;
        }

        return Status.None;
    }
}
