package com.example.rabbitmqtutorial.config;

import com.example.rabbitmqtutorial.consumer.RabbitMqReceiver;
import com.example.rabbitmqtutorial.producer.RabbitMqSender;
import org.springframework.amqp.core.Queue;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

@Configuration
public class RabbitConfig {

    @Bean
    public Queue hello() {
        return new Queue("hello");
    }

    @Bean
    public RabbitMqReceiver receiver1() {
        return new RabbitMqReceiver(1);
    }

    @Bean
    public RabbitMqReceiver receiver2() {
        return new RabbitMqReceiver(2);
    }
}