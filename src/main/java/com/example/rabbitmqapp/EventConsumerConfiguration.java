package com.example.rabbitmqapp;

import org.springframework.amqp.core.*;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class EventConsumerConfiguration {

    @Bean
    public Exchange eventExchange() {
        return new TopicExchange("eventExchange");
    }

    @Bean
    public Queue queue() {
        return new Queue("orderServiceQueue");
    }

    @Bean
    public Binding binding(Queue queue, Exchange eventExchange) {
        return BindingBuilder
                .bind(queue)
                .to(eventExchange)
                .with("customer.*").noargs();
    }

    @Bean
    public EventConsumer eventReceiver() {
        return new EventConsumer();
    }

}
