package com.example.emailservice.config;

import org.springframework.amqp.core.Queue;
import org.springframework.amqp.core.QueueBuilder;
import org.springframework.amqp.rabbit.connection.CachingConnectionFactory;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.listener.MessageListenerContainer;
import org.springframework.amqp.rabbit.listener.SimpleMessageListenerContainer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RabbitConfig {

    private RabbitListener rabbitListener;

    @Autowired
    public void setRabbitListener(RabbitListener rabbitListener){this.rabbitListener = rabbitListener;}

    ConnectionFactory connectionFactory(){
        CachingConnectionFactory connectionFactory = new CachingConnectionFactory("localhost");
        connectionFactory.setUsername("guest");
        connectionFactory.setPassword("guest");
        return connectionFactory;
    }

    Queue registerTokenQueue(){
        return QueueBuilder.durable("registerTokenQueue").build();
    }
    Queue applicationRejectionQueue(){
        return QueueBuilder.durable("applicationRejectionQueue").build();
    }

    @Bean
    MessageListenerContainer messageListenerContainer(){
        SimpleMessageListenerContainer messageListenerContainer = new SimpleMessageListenerContainer();
        messageListenerContainer.setConnectionFactory(connectionFactory());
        messageListenerContainer.setQueues(registerTokenQueue(),applicationRejectionQueue());
//        messageListenerContainer.setQueues(registerTokenQueue());
        messageListenerContainer.setMessageListener(rabbitListener);
        return messageListenerContainer;
    }

}
