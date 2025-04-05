package jaidsonmda.dev.messaging.servicebus;

import com.azure.messaging.servicebus.*;
import jaidsonmda.dev.messaging.MessageConsumer;
import jakarta.annotation.PostConstruct;
import jakarta.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class AzureServiceBusConsumer implements MessageConsumer {

    @Override
    public void consume(String topic, String message) {
        System.out.println("Azure Service Bus - Mensagem consumida do tópico [" + topic + "]: " + message);
    }

    @PostConstruct
    public void init() {
        ServiceBusProcessorClient processor = new ServiceBusClientBuilder()
                .connectionString("<YOUR_SERVICE_BUS_CONNECTION_STRING>")
                .processor()
                .queueName("test-queue")
                .processMessage(context -> consume("test-queue", context.getMessage().getBody().toString()))
                .processError(context -> System.err.println("Erro: " + context.getException()))
                .buildProcessorClient();

        processor.start();
    }
}
