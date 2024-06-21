package org.limepepper.demo;

import javafx.application.Application;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.concurrent.Service;
import javafx.concurrent.Task;
import javafx.concurrent.Worker.State;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;

public class Test1 extends Application {

    private ImageView view;
    private Service<Image> service;

    @Override//   w w  w    . d e    m o   2 s  .  c  o  m 
    public void start(Stage stage) {
        stage.setTitle("Async Demo");

        Group container = new Group();

        Scene scene = new Scene(container, 332, 500);

        view = new ImageView();
        container.getChildren().add(view);

        service = new Service<Image>() {

            @Override
            protected Task createTask() {
                return new Task<Image>() {
                    @Override
                    protected Image call() throws Exception {

                        return new Image("https://farm6.static.flickr.com/5249/5345790221_0e0afe5c71_b.jpg", 332.0,
                                500.0, true, true);
                    }
                };
            }
        };

        service.stateProperty().addListener(new ChangeListener<State>() {
            @Override
            public void changed(ObservableValue<? extends State> state, State oldValue, State newValue) {
                if (newValue == State.SUCCEEDED) {
                    Image image = service.getValue();
                    view.setImage(image);
                }
            }
        });


        service.start();

        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        Application.launch(null);
    }
}
