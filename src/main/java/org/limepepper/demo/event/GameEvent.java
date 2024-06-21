package org.limepepper.demo.event;

import javafx.event.Event;
import javafx.event.EventType;
import org.limepepper.demo.model.Move;

public class GameEvent extends Event {

    private String message;
    private Move move;

    private static final long serialVersionUID = -9038475943759847L;

    //    public static final EventType<GameEvent> ANY = new EventType<>(Event.ANY, "GAME_EVENT");
//    public static final EventType<GameEvent> WAITING = new EventType<>(ANY, "GAME_EVENT_WAITING");
    /**
     * this event represents a request to execute a specific move
      */
    public static final EventType<GameEvent> MOVE = new EventType<>(Event.ANY, "GAME_EVENT_MOVE");
    public static final EventType<GameEvent> MOVED = new EventType<>(Event.ANY, "GAME_EVENT_MOVED");
    /**
     * this event represents a request to wait for a move
     */
    public static final EventType<GameEvent> WAITING = new EventType<>(Event.ANY, "GAME_EVENT_WAITING");
    public static final EventType<GameEvent> DUMP = new EventType<>(Event.ANY, "GAME_EVENT_DUMP");
    public static final EventType<GameEvent> UNDO = new EventType<>(Event.ANY, "GAME_EVENT_UNDO");
    public static final EventType<GameEvent> REDO = new EventType<>(Event.ANY, "GAME_EVENT_REDO");
    public static final EventType<GameEvent> RESTART = new EventType<>(Event.ANY, "GAME_EVENT_RESTART");
    public static final EventType<GameEvent> UPDATED = new EventType<>(Event.ANY, "GAME_EVENT_UPDATED");
    public static final EventType<GameEvent> ANY = MOVE;

    private GameEvent(EventType<? extends Event> eventType, String message, Move move) {
        super(eventType);
        this.message = message;
        this.move = move;
    }

    // <T extends Event>
    public static GameEvent create(EventType<? extends Event> eventType, String message) {
        return new GameEvent(eventType, message, null);
    }

    // <T extends Event>
    public static GameEvent createMove(Move move) {
        return new GameEvent(GameEvent.MOVE, "move", move);
    }

    public static GameEvent createMoved(Move move) {
        return new GameEvent(GameEvent.MOVED, "moved", move);
    }

    public static GameEvent createWaiting() {
        return new GameEvent(GameEvent.WAITING, "waiting", null);
    }

    public static GameEvent createDump() {
        return new GameEvent(GameEvent.DUMP, "dump", null);
    }

    public static GameEvent createUndo() {
        return new GameEvent(GameEvent.UNDO, "undo", null);
    }

    public static GameEvent createRedo() {
        return new GameEvent(GameEvent.REDO, "redo", null);
    }

    // <T extends Event>
    public static GameEvent createRestart() {
        return new GameEvent(GameEvent.RESTART, "restarting", null);
    }

    // <T extends Event>
    public static GameEvent createUpdated(String message) {
        return new GameEvent(GameEvent.UPDATED, message, null);
    }

    public String getMessage() {
        return message;
    }

    public Move getMove() {
        return move;
    }


}
