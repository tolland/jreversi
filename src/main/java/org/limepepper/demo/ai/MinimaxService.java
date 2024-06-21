package org.limepepper.demo.ai;

import javafx.application.Platform;
import javafx.concurrent.Service;
import javafx.concurrent.Task;
import javafx.stage.Stage;
import org.limepepper.demo.event.GameEvent;
import org.limepepper.demo.helper.BoardHelper;
import org.limepepper.demo.model.Board;
import org.limepepper.demo.model.Game;
import org.limepepper.demo.model.Move;

import java.util.List;
import java.util.Random;

/**
 * runs minimax algorithm in a separate thread
 */
public class MinimaxService extends Service<Void> {

    private Game game;
    private Stage stage;

    /**
     *
     */
    private MinimaxService(Stage stage, Game game) {

        this.game = game;
        this.stage = stage;

        // if succeeded
        setOnSucceeded(s -> {
            //code if Service succeeds
            System.out.println("this is running in the constructor - succeeded");
        });

        stage.addEventFilter(
                GameEvent.MOVE,
                event ->
                {
                    System.out.println("handling move in event filter - in service");

                }
        );

        stage.addEventFilter(
                GameEvent.WAITING,
                event ->
                {
                    System.out.println("handling WAITING move in event filter - in service");
                    restart();
                }
        );
    }

    public Move computerMove() {
        System.out.println("making cputer moves"    );
        List<Move> moves = BoardHelper.generateMoves(game.board);
        Random rand = new Random();
        Move move = moves.get(rand.nextInt(moves.size()));
        return move;
    }

//    public static MinimaxService create() {
//        return new MinimaxService();
//    }

    public static MinimaxService create(Stage stage, Game game) {
        return null;
    }


    @Override
    protected Task createTask() {
        return new Task() {

            @Override
            protected Void call() throws Exception {
                Move move = computerMove();
                System.out.println("calling service move " + move);
                Platform.runLater(new Runnable() {
                    @Override public void run() {
                        stage.fireEvent(GameEvent.createMove(move));
                    }
                });

                System.out.println("event fired");
                return null;

            }

//            @Override
//            protected void succeeded() {
//                super.succeeded();
//                System.out.println("Succeeded service taskccc");
//            }
        };
    }

//    // add event listener for change state events
//    @Override
//    protected void succeeded() {
//        super.succeeded();
//        System.out.println("Succeeded service111");
//    }

    @Override
    protected void failed() {
        super.failed();
        System.out.println("Failed service");
        throw new RuntimeException(getException());
    }

    @Override
    protected void cancelled() {
        super.cancelled();
        System.out.println("Cancelled service");
    }

    @Override
    protected void scheduled() {
        super.scheduled();
        System.out.println("Scheduled service");
    }

    @Override
    protected void ready() {
        super.ready();
        System.out.println("Ready service");
    }

    @Override
    protected void running() {
        super.running();
        System.out.println("Running service11");
    }


}
