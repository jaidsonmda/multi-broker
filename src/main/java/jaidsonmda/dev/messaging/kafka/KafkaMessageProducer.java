package jaidsonmda.dev.messaging.kafka;

import jaidsonmda.dev.messaging.MessageProducer;
import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.ProducerRecord;


public class KafkaMessageProducer implements MessageProducer {
    private final KafkaProducer<String, String> kafkaTemplate;

    public KafkaMessageProducer(KafkaProducer<String, String> kafkaTemplate) {
        this.kafkaTemplate = kafkaTemplate;
    }

    @Override
    public void sendMessage(String topic, String message) {
        ProducerRecord<String, String> kvProducerRecord;
        kvProducerRecord = new ProducerRecord<>(topic,message);
        kafkaTemplate.send(kvProducerRecord);
        System.out.println("Kafka - Mensagem enviada: " + message);
    }
}
