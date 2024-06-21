package org.limepepper.demo.bloc;

import org.limepepper.bloc.Bloc;

public class GameBloc extends Bloc<GameEvent, GameState> {
    public GameBloc(GameState initialState) {
        super(initialState);
    }
}
