package jaidsonmda.dev.messaging.kafka;

import jaidsonmda.dev.messaging.MessageConsumer;
import jakarta.enterprise.context.ApplicationScoped;
import org.eclipse.microprofile.reactive.messaging.Incoming;

@ApplicationScoped
public class KafkaMessageConsumer implements MessageConsumer {

    @Override
    public void consume(String topic, String message) {
        System.out.println("Kafka - Mensagem consumida do tópico [" + topic + "]: " + message);
    }

    @Incoming("kafka-in")
    public void receive(String message) {
        consume("kafka-in", message);
    }
}

