package com.example.rabbitmqtutorial;

import org.springframework.amqp.core.Queue;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;

import java.util.concurrent.TimeUnit;

@SpringBootApplication
@EnableScheduling
public class RabbitmqTutorialApplication {

    public static void main(String[] args) {
        SpringApplication.run(RabbitmqTutorialApplication.class, args);
    }

//
//    @Autowired
//    private ConfigurableApplicationContext ctx;
//
//    @Scheduled(initialDelay = 30_000)
//    public void shutdownApp() {
//        System.out.println("Shutting the application down!");
//        ctx.close();
//    }
}
