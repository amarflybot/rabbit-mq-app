package com.example.rabbitmqapp;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.amqp.core.TopicExchange;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.amqp.core.Exchange;

@Configuration
public class EventProducerConfiguration {

    @Bean
    public Exchange eventExchange() {
        return new TopicExchange("eventExchange");
    }

    @Bean
    public CustomerService customerService(RabbitTemplate rabbitTemplate,
                                           Exchange senderTopicExchange,
                                           ObjectMapper objectMapper) {
        return new CustomerService(rabbitTemplate, senderTopicExchange, objectMapper);
    }

}
