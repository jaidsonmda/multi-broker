package jaidsonmda.dev.messaging.rabbitmq;

import io.smallrye.reactive.messaging.MutinyEmitter;
import jaidsonmda.dev.messaging.MessageProducer;
import jakarta.inject.Inject;
import org.eclipse.microprofile.reactive.messaging.Channel;



public class RabbitMqProducer implements MessageProducer {

    @Inject
    @Channel("rabbitmq-out")
    MutinyEmitter<String> emitter;

    @Override
    public void sendMessage(String topic, String message) {
        emitter.send(message);
        System.out.println("RabbitMQ - Mensagem enviada: " + message);
    }
}
