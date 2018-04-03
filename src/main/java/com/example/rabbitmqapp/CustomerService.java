package com.example.rabbitmqapp;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.amqp.core.Exchange;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.core.MessageBuilder;
import org.springframework.amqp.core.MessagePropertiesBuilder;
import org.springframework.amqp.rabbit.core.RabbitTemplate;

public class CustomerService {

    private final RabbitTemplate rabbitTemplate;

    private final Exchange exchange;
    private final ObjectMapper objectMapper;

    public CustomerService(RabbitTemplate rabbitTemplate, Exchange exchange, ObjectMapper objectMapper) {
        this.rabbitTemplate = rabbitTemplate;
        this.exchange = exchange;
        this.objectMapper = objectMapper;
    }

    public void createCustomer(Customer customer) {
        // ... do some database stuff
        String routingKey = "customer.created";
        String json = null;
        try {
            json = objectMapper.writeValueAsString(customer);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        Message jsonMessage = MessageBuilder.withBody(json.getBytes())
                .andProperties(MessagePropertiesBuilder.newInstance().setContentType("application/json")
                        .build()).build();
        rabbitTemplate.send(exchange.getName(), routingKey, jsonMessage);
    }
}
