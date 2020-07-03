package model;

public enum TypeDifficulty {
    Easy, Medium, Hard;

    public static TypeDifficulty getValue(int index)
    {
        return values()[index];
    }
}
