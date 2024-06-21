package org.limepepper.demo.command;

import java.util.Stack;

public interface UndoListener {
    void onEvent(Stack<Command> undoStack, Stack<Command> redoStack);
}
