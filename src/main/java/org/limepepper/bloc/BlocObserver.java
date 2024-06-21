package org.limepepper.bloc;

abstract class BlocObserver {

    void onCreate(BlocBase bloc) {
    }

    void onEvent(BlocBase bloc, Event event) {
    }

    void onChange(BlocBase bloc, Change change) {
    }

    void onClose(BlocBase bloc) {
    }

    void onError(BlocBase bloc, Object error, StackTraceElement stackTrace) {
    }

    void onTransition(BlocBase bloc, Transition transition) {
    }
}
