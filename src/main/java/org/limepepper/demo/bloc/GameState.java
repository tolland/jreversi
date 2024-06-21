package org.limepepper.demo.bloc;

import org.limepepper.demo.model.Player;

public class GameState {

    private final Player toPlay;

    public GameState(Player toPlay) {
        this.toPlay = toPlay;
    }

    static GameState empty() {
        return new GameState(null);
    }

    static GameState copyWith(Player toPlay) {
        return new GameState(
                toPlay
        );
    }

    @Override
    public String toString() {
        return String.format("iwufheiwufheXXX %s ",toPlay);
    }
}
