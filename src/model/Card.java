package model;

import java.awt.*;

public class Card
{
    private int row;
    private int column;
    private Image image;
    private boolean open;
    private boolean deleted;
    private static Image frontImage;
    private static Image deleteImage;

    public Card(int row, int column, Image image) {
        this.row = row;
        this.column = column;
        this.image = image;
    }

    public static void setFrontImage(Image image)
    {
        frontImage = image;
    }
    public static void setDeleteImage(Image image)
    {
        deleteImage = image;
    }

    public Image getDisplayImage()
    {
        if (isDeleted())
            return deleteImage;
        if (open)
            return image;
        return frontImage;
    }

    public boolean isOpen() {
        return open;
    }

    public void setOpen(boolean open) {
        this.open = open;
    }

    public int getRow() {
        return row;
    }

    public int getColumn() {
        return column;
    }

    public boolean isDeleted() {
        return deleted;
    }

    public void setDeleted(boolean deleted) {
        this.deleted = deleted;
    }
}
