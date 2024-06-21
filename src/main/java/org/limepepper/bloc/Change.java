package org.limepepper.bloc;

public class Change<State> {
    private final State currentState;
    private final State nextState;

    public Change(State currentState, State nextState) {
        this.currentState = currentState;
        this.nextState = nextState;

    }

    public String toString() {
        return String.format("Change { currentState: $currentState, nextState: $nextState }", currentState, nextState);
    }
}
