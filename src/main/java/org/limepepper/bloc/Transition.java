package org.limepepper.bloc;

public class Transition<Event, State> extends Change<State> {

    final Event event;

    public Transition(State currentState, Event event, State nextState) {
        super(currentState, nextState);
        this.event = event;
    }
}
