package jaidsonmda.dev.messaging.servicebus;

import com.azure.messaging.servicebus.*;
import jaidsonmda.dev.messaging.MessageProducer;
import jakarta.annotation.PostConstruct;
import jakarta.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class AzureServiceBusProducer implements MessageProducer {

    private ServiceBusSenderClient senderClient;

    @PostConstruct
    public void init() {
        senderClient = new ServiceBusClientBuilder()
                .connectionString("<YOUR_SERVICE_BUS_CONNECTION_STRING>")
                .sender()
                .queueName("test-queue")
                .buildClient();
    }

    @Override
    public void sendMessage(String topic, String message) {
        senderClient.sendMessage(new ServiceBusMessage(message));
        System.out.println("Azure Service Bus - Mensagem enviada: " + message);
    }
}
