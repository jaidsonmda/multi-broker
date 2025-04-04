package jaidsonmda.dev.messaging;

public interface MessageConsumer {
    void consume(String topic, String message);
}
