package com.example.rabbitbrockerapplication;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.annotation.EnableRabbit;
import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;
@EnableRabbit
@Component
public class RabbitMQListener {

    Logger logger= LoggerFactory.getLogger(RabbitMQListener.class);
    @RabbitListener(queues = "myQueue1")
    public void processMyQueue(String message) {
        logger.info("Received from myQueue: {}", message);
    }
    @RabbitListener(queues = "myQueue2")
    public void processMyQueue2(String message) {
        logger.info("Received second from myQueue: {}", message);
    }
}
