package service;

public class ApplicationHelper { // ?
    private static ApplicationHelper instance = new ApplicationHelper();
    private String login;

    public static ApplicationHelper getInstance() {
        return instance;
    }

    private ApplicationHelper()
    {
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }
}
