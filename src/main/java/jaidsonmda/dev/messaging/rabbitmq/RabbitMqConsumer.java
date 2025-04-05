package jaidsonmda.dev.messaging.rabbitmq;

import jaidsonmda.dev.messaging.MessageConsumer;
import jakarta.enterprise.context.ApplicationScoped;
import org.eclipse.microprofile.reactive.messaging.Incoming;

@ApplicationScoped
public class RabbitMqConsumer implements MessageConsumer {

    @Override
    public void consume(String topic, String message) {
        System.out.println("RabbitMQ - Mensagem consumida do tópico [" + topic + "]: " + message);
    }

    @Incoming("rabbitmq-in")
    public void receive(String message) {
        consume("rabbitmq-in", message);
    }
}