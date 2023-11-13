package com.example.rabbitbrockerapplication;

import org.slf4j.Logger;
import org.springframework.amqp.core.*;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.connection.CachingConnectionFactory;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitAdmin;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.rabbit.listener.SimpleMessageListenerContainer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RabbitConfiguration {

    Logger logger = LoggerFactory.getLogger(RabbitConfiguration.class);

    @Bean
    public ConnectionFactory connectionFactory() {
        return new CachingConnectionFactory("localhost");
    }

    @Bean
    public AmqpAdmin amqpAdmin() {
        return new RabbitAdmin(connectionFactory());
    }

    @Bean
    public RabbitTemplate rabbitTemplate() {
        return new RabbitTemplate(connectionFactory());
    }

    @Bean
    public Queue myQueue1() {
        return new Queue("myQueue1");
    }

    @Bean
    public Queue myQueue2() {
        return new Queue("myQueue2");
    }

    public FanoutExchange fanoutExchange() {
        return new FanoutExchange("common-exchange");
    }

    public DirectExchange directExchange() {
        return new DirectExchange("direct-exchange");
    }
    @Bean
    public Binding binding1() {
        return BindingBuilder.bind(myQueue1()).to(directExchange()).with("error");
    }

    @Bean
    public Binding binding2() {
        return BindingBuilder.bind(myQueue2()).to(directExchange()).with("warning");
    }

    @Bean
    public Binding binding3() {
        return BindingBuilder.bind(myQueue2()).to(directExchange()).with("info");
    }

    @Bean
    public TopicExchange topicExchange() {
        return new TopicExchange("topic-exchange");
    }

    @Bean
    public Binding binding11() {
        return BindingBuilder.bind(myQueue1()).to(topicExchange()).with("one.*");
    }

    @Bean
    public Binding binding12() {
        return BindingBuilder.bind(myQueue2()).to(topicExchange()).with("*.second");
    }






//    @Bean
//    public SimpleMessageListenerContainer messageListenerContainer() {
//        SimpleMessageListenerContainer container = new SimpleMessageListenerContainer();
//        //Здесь мы указываем название очередей на которые подписываемся
//        container.setConnectionFactory(connectionFactory());
//        container.setQueueNames("myQueue");
//        //Также что нам нужно сделать при получении сообщения, в данном случае мы прописываеи logger
//        container.setMessageListener(message -> logger.info("Received from myQueue : " + new String(message.getBody())));
//        return container;
//    }

}
