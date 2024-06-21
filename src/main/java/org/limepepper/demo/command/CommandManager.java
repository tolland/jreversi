package org.limepepper.demo.command;

import java.util.ArrayList;
import java.util.Stack;

public class CommandManager {
    private final Stack<Command> undoStack = new Stack<>();
    private final Stack<Command> redoStack = new Stack<>();

    private static CommandManager instance;

    private CommandManager() {
        //FXCollections.obs
    }

    public static CommandManager getInstance() {
        if (instance == null) {
            instance = new CommandManager();
        }
        return instance;
    }

    public void executeCommand(Command command) {
        command.execute();
        undoStack.push(command);
        redoStack.clear();
        notifyObservers();
    }

    public void undo() {
        if (!undoStack.isEmpty()) {
            Command command = undoStack.pop();
            command.undo();
            redoStack.push(command);
            notifyObservers();
        }
    }

    public void redo() {
        if (!redoStack.isEmpty()) {
            Command command = redoStack.pop();
            command.redo();
            undoStack.push(command);
            notifyObservers();
        }
    }

    public void reset() {
        redoStack.clear();
        undoStack.clear();
        notifyObservers();
    }

    public void notifyObservers() {
        listeners.forEach(listener -> {
            listener.onEvent(undoStack, redoStack);
        });
    }

    ArrayList<UndoListener> listeners = new ArrayList<>();

    public void addListener(UndoListener listener) {
        listeners.add(listener);
    }

//    public <CommandListener> void addListener(CommandListener listener) {
//        undoStack.forEach(listener::onCommandExecuted);
//    }

}
