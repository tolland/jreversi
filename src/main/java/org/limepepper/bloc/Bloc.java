package org.limepepper.bloc;

public class Bloc<Event, State> extends BlocBase<State> implements BlocEventSink<Event> {

    static BlocObserver _blocObserver = new _DefaultBlocObserver();

    public Bloc(State initialState) {
        super(initialState);
    }



    public void add(Event event) {
    }

    public void close() {
    }

    <E extends Event> void on() {

    }
}
