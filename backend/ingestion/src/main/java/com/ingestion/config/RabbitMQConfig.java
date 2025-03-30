package com.ingestion.config;

import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.core.*;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RabbitMQConfig {

    @Value("${spring.rabbitmq.queues.rideQueue}")
    private String rideQueue;

    @Value("${spring.rabbitmq.queues.deadLetterQueue}")
    private String deadLetterQueue;

    @Value("${spring.rabbitmq.exchange.rideExchange}")
    private String rideExchange;

    @Value("${spring.rabbitmq.routing.rideRoutingKey}")
    private String rideRoutingKey;

    @Value("${spring.rabbitmq.routing.deadLetterRoutingKey}")
    private String deadLetterRoutingKey;

    @Bean
    public Queue rideQueue() {
        return QueueBuilder.durable(rideQueue)
                .deadLetterExchange(rideExchange)
                .deadLetterRoutingKey(deadLetterRoutingKey)
                .build();
    }

    @Bean
    public Jackson2JsonMessageConverter jsonMessageConverter() {
        return new Jackson2JsonMessageConverter();
    }

    @Bean
    public Queue deadLetterQueue() {
        return QueueBuilder.durable(deadLetterQueue).build();
    }

    @Bean
    public DirectExchange rideExchange() {
        return new DirectExchange(rideExchange);
    }

    @Bean
    public Binding rideBinding() {
        return BindingBuilder.bind(rideQueue())
                .to(rideExchange())
                .with(rideRoutingKey);
    }

    @Bean
    public Binding deadLetterBinding() {
        return BindingBuilder.bind(deadLetterQueue())
                .to(rideExchange())
                .with(deadLetterRoutingKey);
    }

    @Bean
    public RabbitTemplate rabbitTemplate(ConnectionFactory connectionFactory) {
        RabbitTemplate template = new RabbitTemplate((org.springframework.amqp.rabbit.connection.ConnectionFactory) connectionFactory);
        template.setMessageConverter(jsonMessageConverter());
        return template;
    }

}
