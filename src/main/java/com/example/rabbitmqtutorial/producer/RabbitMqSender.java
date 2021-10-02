package com.example.rabbitmqtutorial.producer;

import lombok.RequiredArgsConstructor;
import org.springframework.amqp.core.DirectExchange;
import org.springframework.amqp.core.TopicExchange;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.scheduling.annotation.Scheduled;

import java.util.concurrent.atomic.AtomicInteger;

@RequiredArgsConstructor
public class RabbitMqSender {

    private final RabbitTemplate template;
    private final TopicExchange topicExchange;

    AtomicInteger index = new AtomicInteger(0);
    AtomicInteger count = new AtomicInteger(0);

    private final String[] keys = {"quick.orange.rabbit", "lazy.orange.elephant", "quick.orange.fox",
            "lazy.brown.fox", "lazy.pink.rabbit", "quick.brown.fox"};

    @Scheduled(fixedDelay = 1000, initialDelay = 500)
    public void send() {
        StringBuilder builder = new StringBuilder("Hello to ");
        if (this.index.incrementAndGet() == 3) {
            this.index.set(0);
        }
        String key = keys[this.index.get()];
        builder.append(key).append(' ');
        builder.append(this.count.incrementAndGet());
        String message = builder.toString();
        template.convertAndSend(topicExchange.getName(), key, message);
        System.out.println(" [x] Sent '" + message + "'");
    }
}
