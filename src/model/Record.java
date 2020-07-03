package model;

public class Record {
    private String login;
    private int countError;
    private TypeDifficulty typeDifficulty;

    public Record(String login, int countError, TypeDifficulty typeDifficulty) {
        this.login = login;
        this.countError = countError;
        this.typeDifficulty = typeDifficulty;
    }

    public String getLogin() {
        return login;
    }

    public int getCountError() {
        return countError;
    }
    public TypeDifficulty getTypeDifficulty() {
        return typeDifficulty;
    }

    public String getDifficultyString()
    {
        if (getTypeDifficulty() == TypeDifficulty.Easy)
            return "Легкий";
        else if (getTypeDifficulty() == TypeDifficulty.Medium)
            return  "Средний";
        else
            return  "Сложный";
    }
}
