package model;

import controllers.Controller;

public interface IGameView {
    void showMessage(String message);

    void changeStateCard(Card card);

    void setController(Controller controller);

    void initView();

    void showView();
}
