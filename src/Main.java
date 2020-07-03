import controllers.Controller;
import views.LoginView;

public class Main {
    public static void main(String[] args)
    {
        LoginView loginView = new LoginView();
        Controller controller = new Controller(loginView);
        loginView.setController(controller);
        loginView.showView();
    }
}
