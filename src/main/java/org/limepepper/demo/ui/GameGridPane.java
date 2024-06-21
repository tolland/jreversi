package org.limepepper.demo.ui;

import javafx.scene.image.Image;
import javafx.scene.layout.GridPane;
import javafx.scene.text.Text;
import org.limepepper.demo.Config;
import org.limepepper.demo.event.GameEvent;
import org.limepepper.demo.model.Board;
import org.limepepper.demo.model.Game;

import java.io.FileInputStream;
import java.io.FileNotFoundException;

public class GameGridPane extends GridPane {

    private Game game;

    final Image image_green;
    final Image image_dark;
    final Image image_white;

    private GameGridPane() {
        super();

        setHgap(5);
        setVgap(5);

        FileInputStream image_file_green = null;
        FileInputStream image_file_dark = null;
        FileInputStream image_file_white = null;
        try {
            image_file_green = new FileInputStream("src/main/resources/images/green.png");
            image_file_dark = new FileInputStream("src/main/resources/images/dark.png");
            image_file_white = new FileInputStream("src/main/resources/images/light.png");
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }
        image_green = new Image(image_file_green, 60, 60, false, false);
        image_dark = new Image(image_file_dark, 60, 60, false, false);
        image_white = new Image(image_file_white, 60, 60, false, false);
    }

    public static GameGridPane create(Game game) {
        GameGridPane gridpane = new GameGridPane();
        gridpane.game = game;
        return gridpane;
    }

    public void renderBoard() {
        Board board = game.getBoard();
        getChildren().clear();
        for (int y = 1; y <= Config.HEIGHT; y++) {
            add(new Text("" + y), 0, y);
        }
        for (int x = 1; x <= Config.WIDTH; x++) {
            add(new Text(Config.chars.get(x).toString()), x, 9);
        }


    }

    public void updateBoard() {
        for (int y = 1; y <= Config.HEIGHT; y++) {
            for (int x = 1; x <= Config.WIDTH; x++) {
                GameGridTile tile = GameGridTile.create(x, y, game.getBoard().getTile(x, y));
                add(tile, x, y);
            }
        }
        this.fireEvent(GameEvent.createUpdated(game.getCurrentPlayer().toString()));
        System.out.println("to play " + game.getCurrentPlayer());
    }
}
