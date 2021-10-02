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
    public TopicExchange topic() {
        return new TopicExchange("tut.topic");
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
        public Binding binding1a(TopicExchange topic, Queue autoDeleteQueue1) {
            return BindingBuilder.bind(autoDeleteQueue1).to(topic).with("*.orange.*");
        }

        @Bean
        public Binding binding1b(TopicExchange topic, Queue autoDeleteQueue1) {
            return BindingBuilder.bind(autoDeleteQueue1).to(topic).with("*.*.rabbit");
        }

        @Bean
        public Binding binding2a(TopicExchange topic, Queue autoDeleteQueue2) {
            return BindingBuilder.bind(autoDeleteQueue2).to(topic).with("lazy.#");
        }

        @Bean
        public RabbitMqReceiver receiver() {
            return new RabbitMqReceiver();
        }

    }

    @Profile("sender")
    @Bean
    public RabbitMqSender sender(RabbitTemplate rabbitTemplate, TopicExchange exchange) {
        return new RabbitMqSender(rabbitTemplate, exchange);
    }
}

