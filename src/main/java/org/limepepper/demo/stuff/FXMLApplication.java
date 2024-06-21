package org.limepepper.demo.stuff;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import org.limepepper.demo.GameController;

public class FXMLApplication extends Application {

    public static void main1(String[] args) {
        Application.launch(FXMLApplication.class);
    }

    @Override
    public void start(Stage stage) throws Exception {
        //Parent root = FXMLLoader.load(getClass().getResource("hello-view.fxml"));
        Parent root = FXMLLoader.load(getClass().getResource("hello-view.fxml"));
        //def scene = new Scene(fxmlLoader.load() as Parent, 320, 240)

        Scene scene = new Scene(root, 300, 275);

        stage.setTitle("FXML Welcome");
        stage.setScene(scene);
        stage.show();
    }

    public GameController getGame() {
        return game;
    }

    public void setGame(GameController game) {
        this.game = game;
    }

    private GameController game;
}
