package org.limepepper.demo.command;

import org.limepepper.demo.model.Game;

public class MakeMove implements Command {
    private final int x;
    private final int y;
    private final Game game;

    public MakeMove(Game game, int x, int y) {
        this.x = x;
        this.y = y;
        this.game = game;
    }

    @Override
    public void execute() {
        // make move
        System.out.println("Making move at " + x + ", " + y);
        game.makeMove(x, y, game.getCurrentPlayer());

    }

    @Override
    public void undo() {
        // undo move
        System.out.println("Undoing move at " + x + ", " + y);
        game.undo();
    }

    @Override
    public void redo() {
        // redo move
        System.out.println("Redoing move at " + x + ", " + y);
        game.makeMove(x, y, game.getCurrentPlayer());
    }
}
