package jaidsonmda.dev.messaging;

public interface MessageProducer {
    void sendMessage(String topic, String message);
}
