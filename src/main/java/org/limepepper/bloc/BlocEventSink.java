package org.limepepper.bloc;

interface BlocEventSink<Event> extends ErrorSink {
    /// Adds an [event] to the sink.
    ///
    /// Must not be called on a closed sink.
    void add(Event event);
}
