package org.limepepper.demo.ui;

import javafx.event.EventHandler;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;
import org.limepepper.demo.event.GameEvent;
import org.limepepper.demo.model.Move;
import org.limepepper.demo.model.Tile;

import java.io.FileInputStream;
import java.io.FileNotFoundException;

public class GameGridTile extends StackPane {

    private final int x, y;
    private final Tile tile;
    private final Text text;
    private final ImageView imageView;

    static final Image image_green;
    static final Image image_dark;
    static final Image image_white;

    static FileInputStream image_file_green = null;
    static FileInputStream image_file_dark = null;
    static FileInputStream image_file_white = null;

    static {
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

    public static GameGridTile create(int x, int y, Tile tile) {
        return new GameGridTile(x, y, tile);
    }

    private GameGridTile(int x, int y, Tile tile) {
        this.x = x;
        this.y = y;
        this.tile = tile;
        this.text = new Text();

        //this.imageView = imageView;
        switch (tile) {
            case NONE -> imageView = new ImageView(image_green);
            case DARK -> {
                imageView = new ImageView(image_dark);
                text.setFill(Color.WHEAT);
            }
            case LIGHT -> imageView = new ImageView(image_white);
            default -> throw new IllegalArgumentException("Invalid tile");
        }

        int finalX = x;
        int finalY = y;
        imageView.addEventHandler(
                MouseEvent.MOUSE_CLICKED,
                new EventHandler<MouseEvent>() {

                    @Override
                    public void handle(MouseEvent event) {
                        //handleMove(finalX, finalY);
                      //  System.out.println("event = " + event);
                        fireEvent(GameEvent.createMove(new Move(finalX, finalY)));
                        event.consume();
                    }
                });

        text.setText("" + x + "," + y);

        text.setMouseTransparent(true);

        getChildren().addAll(imageView, text);

    }
}
