package com.example.rabbitmqtutorial.config;

import com.example.rabbitmqtutorial.consumer.RabbitMqReceiver;
import com.example.rabbitmqtutorial.producer.RabbitMqSender;
import org.springframework.amqp.core.*;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

@Configuration
public class RabbitConfig {


    @Bean
    public DirectExchange direct() {
        return new DirectExchange("tut.direct");
    }

    @Profile("receiver")
    private static class ReceiverConfig {

        @Bean
        public Queue autoDeleteQueue1() {
            return new AnonymousQueue();
        }

        @Bean
        public Queue autoDeleteQueue2() {
            return new AnonymousQueue();
        }

        @Bean
        public Binding binding1a(DirectExchange direct, Queue autoDeleteQueue1) {
            return BindingBuilder.bind(autoDeleteQueue1).to(direct).with("orange");
        }

        @Bean
        public Binding binding1b(DirectExchange direct, Queue autoDeleteQueue1) {
            return BindingBuilder.bind(autoDeleteQueue1).to(direct).with("black");
        }

        @Bean
        public Binding binding2a(DirectExchange direct, Queue autoDeleteQueue2) {
            return BindingBuilder.bind(autoDeleteQueue2).to(direct).with("green");
        }

        @Bean
        public Binding binding2b(DirectExchange direct, Queue autoDeleteQueue2) {
            return BindingBuilder.bind(autoDeleteQueue2).to(direct).with("black");
        }

        @Bean
        public RabbitMqReceiver receiver() {
            return new RabbitMqReceiver();
        }

    }

    @Profile("sender")
    @Bean
    public RabbitMqSender sender(RabbitTemplate rabbitTemplate, DirectExchange exchange) {
        return new RabbitMqSender(rabbitTemplate, exchange);
    }
}

