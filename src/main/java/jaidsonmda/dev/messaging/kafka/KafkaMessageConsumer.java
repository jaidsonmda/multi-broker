package jaidsonmda.dev.messaging.kafka;

import jaidsonmda.dev.messaging.MessageConsumer;
import org.eclipse.microprofile.reactive.messaging.Incoming;

public class KafkaMessageConsumer implements MessageConsumer {
    @Override
    public void consume(String topic, String message) {
        System.out.println("Kafka - Mensagem consumida do tópico [" + topic + "]: " + message);
    }

    @Incoming("prices")
    public void listen(String message) {
        consume("test-topic", message);
    }
}

