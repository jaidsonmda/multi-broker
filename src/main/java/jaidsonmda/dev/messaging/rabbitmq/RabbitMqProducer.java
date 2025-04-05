package jaidsonmda.dev.messaging.rabbitmq;

import jaidsonmda.dev.messaging.MessageProducer;
import jakarta.inject.Inject;
import org.eclipse.microprofile.reactive.messaging.Channel;
import org.eclipse.microprofile.reactive.messaging.Emitter;


public class RabbitMqProducer implements MessageProducer {

    @Inject
    @Channel("rabbitmq-out")
    Emitter<String> emitter;

    @Override
    public void sendMessage(String topic, String message) {
        emitter.send(message);
        System.out.println("RabbitMQ - Mensagem enviada: " + message);
    }
}
