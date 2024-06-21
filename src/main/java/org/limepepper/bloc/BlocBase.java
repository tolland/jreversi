package org.limepepper.bloc;

public abstract class BlocBase<State> {

    private final State _state;
    private final BlocObserver _blocObserver = new _DefaultBlocObserver();

    public BlocBase(State state) {
        this._state = state;
        _blocObserver.onCreate(this);
    }


}
