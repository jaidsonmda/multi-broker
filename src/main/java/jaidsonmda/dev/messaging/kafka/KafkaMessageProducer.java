package jaidsonmda.dev.messaging.kafka;

import io.smallrye.reactive.messaging.MutinyEmitter;
import jaidsonmda.dev.messaging.MessageProducer;

import io.smallrye.reactive.messaging.kafka.KafkaRecord;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import org.eclipse.microprofile.reactive.messaging.Channel;

@ApplicationScoped
public class KafkaMessageProducer implements MessageProducer {

    @Inject
    @Channel("kafka-out")
            @SuppressWarnings({})
    MutinyEmitter<String> emitter;

    @Override
    public void sendMessage(String topic, String message) {
        emitter.send(String.valueOf(KafkaRecord.of(topic, message)));
        System.out.println("Kafka - Mensagem enviada: " + message);
    }
}
