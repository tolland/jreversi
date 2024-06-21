package org.limepepper.demo.command;

import org.limepepper.demo.GameController;
import org.limepepper.demo.model.Game;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class MakeMove implements Command {
    private static final Logger logger = LoggerFactory.getLogger(MakeMove.class);

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
        logger.trace("Making move at " + x + ", " + y);
        game.makeMove(x, y, game.getCurrentPlayer());

    }

    @Override
    public void undo() {
        // undo move
        logger.debug("Undoing move at " + x + ", " + y);
        game.undo();
    }

    @Override
    public void redo() {
        // redo move
        logger.trace("Redoing move at " + x + ", " + y);
        game.makeMove(x, y, game.getCurrentPlayer());
    }
}
