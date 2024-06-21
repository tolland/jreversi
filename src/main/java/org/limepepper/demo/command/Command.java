package org.limepepper.demo.command;

public interface Command {
    void execute();
    void undo();
    void redo();
}
