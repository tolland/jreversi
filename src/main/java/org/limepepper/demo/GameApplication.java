package org.limepepper.demo;

import com.almasb.fxeventbus.EventBus;
import com.almasb.fxeventbus.FXEventBus;
import javafx.application.Application;
import javafx.stage.Stage;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class GameApplication extends Application {
    private static final Logger logger = LoggerFactory.getLogger(GameApplication.class);

   // public static EventBus eventBus = new FXEventBus();

    public static void main(String[] args) {
        Application.launch(GameApplication.class);
    }

    @Override
    public void start(Stage stage) {

        stage.setOnHidden(e -> {
            logger.debug("stage is hidden");
        });

        stage.setOnShown(e -> {
            logger.debug("stage is shown");
        });

        stage.getProperties().put("hostServices", this.getHostServices());

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
