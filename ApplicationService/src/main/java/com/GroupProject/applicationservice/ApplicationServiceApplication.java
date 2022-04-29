package com.GroupProject.applicationservice;

import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.orm.jpa.HibernateJpaAutoConfiguration;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;


//exclude aws & hibernate auto configeration
@SpringBootApplication(
        exclude = {
                HibernateJpaAutoConfiguration.class,
                org.springframework.cloud.aws.autoconfigure.context.ContextInstanceDataAutoConfiguration.class,
                org.springframework.cloud.aws.autoconfigure.context.ContextStackAutoConfiguration.class,
                org.springframework.cloud.aws.autoconfigure.context.ContextRegionProviderAutoConfiguration.class
        })
@EnableEurekaClient
public class ApplicationServiceApplication {

    private static RabbitTemplate rabbitTemplate;

    @Autowired
    public void setRabbitTemplate(RabbitTemplate rabbitTemplate) {
        this.rabbitTemplate = rabbitTemplate;
    }


    public static void main(String[] args) {
        SpringApplication.run(ApplicationServiceApplication.class, args);
//        rabbitTemplate.convertAndSend("Application","rejection","hiephuynh224@gmail.com+++application rejected");
    }

}
