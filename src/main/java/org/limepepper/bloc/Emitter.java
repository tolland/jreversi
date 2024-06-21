package org.limepepper.bloc;

/// {@template emitter}
/// An [Emitter] is a class which is capable of emitting new states.
///
/// See also:
///
/// * [EventHandler] which has access to an [Emitter].
///
/// {@endtemplate}
abstract class Emitter<State> {


    private boolean isDone;

//    Future<void> onEach<T>(
//    Stream<T> stream, {
//        required void Function(T data) onData,
//        void Function(Object error, StackTrace stackTrace)? onError,
//    });

    abstract void call(State state);

    public boolean isDone() {
        return isDone;
    }
}
