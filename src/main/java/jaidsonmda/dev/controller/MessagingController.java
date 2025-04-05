package jaidsonmda.dev.controller;


import jaidsonmda.dev.messaging.kafka.KafkaMessageConsumer;
import jaidsonmda.dev.messaging.kafka.KafkaMessageProducer;
import jaidsonmda.dev.messaging.rabbitmq.RabbitMqConsumer;
import jaidsonmda.dev.messaging.rabbitmq.RabbitMqProducer;
import jaidsonmda.dev.messaging.servicebus.AzureServiceBusConsumer;
import jaidsonmda.dev.messaging.servicebus.AzureServiceBusProducer;
import jakarta.inject.Inject;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;



@Path("/api/messaging")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class MessagingController {

    @Inject
    KafkaMessageProducer kafkaProducer;

    @Inject
    RabbitMqProducer rabbitProducer;

    @Inject
    AzureServiceBusProducer serviceBusProducer;

    @Inject
    AzureServiceBusConsumer azureServiceBusConsumer;
    @Inject
    RabbitMqConsumer rabbitMqConsumer;
    @Inject
    KafkaMessageConsumer kafkaMessageConsumer;

    @POST
    @Path("/send/kafka")
    public Response sendKafka(@QueryParam("topic") String topic, @QueryParam("message") String message) {
        kafkaProducer.sendMessage(topic, message);
        return Response.ok("Mensagem enviada via Kafka!").build();
    }

    @POST
    @Path("/send/rabbit")
    public Response sendRabbit(@QueryParam("topic") String topic, @QueryParam("message") String message) {
        rabbitProducer.sendMessage(topic, message);
        return Response.ok("Mensagem enviada via RabbitMQ!").build();
    }

    @POST
    @Path("/send/servicebus")
    public Response sendServiceBus(@QueryParam("topic") String topic, @QueryParam("message") String message) {
        serviceBusProducer.sendMessage(topic, message);
        return Response.ok("Mensagem enviada via Azure Service Bus!").build();
    }

    @GET
    @Path("/consume/kafka")
    public Response consumeKafka(@QueryParam("message") String message) {
        kafkaMessageConsumer.receive(message);
        return Response.ok("Consumo de Kafka está ativo por listener interno!").build();
    }

    @GET
    @Path("/consume/rabbit")
    public Response consumeRabbit(@QueryParam("message") String message) {
        rabbitMqConsumer.receive(message);
        return Response.ok("Consumo de RabbitMQ está ativo por listener interno!").build();
    }

    @GET
    @Path("/consume/servicebus")
    public Response consumeServiceBus(@QueryParam("topic") String topic, @QueryParam("message") String message) {
        azureServiceBusConsumer.consume(topic,message);
        return Response.ok("Consumo de Azure Service Bus está ativo por listener interno!").build();
    }
}

