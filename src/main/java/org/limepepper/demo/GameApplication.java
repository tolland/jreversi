package org.limepepper.demo;

import javafx.application.Application;
import javafx.stage.Stage;

public class GameApplication extends Application {

    public static void main(String[] args) {
        Application.launch(GameApplication.class);
    }

    @Override
    public void start(Stage stage) {
        game = new GameController(stage);
        game.startNewGame();
    }

    public GameController getGame() {
        return game;
    }

    public void setGame(GameController game) {
        this.game = game;
    }

    private GameController game;
}
