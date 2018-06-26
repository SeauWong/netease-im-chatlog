package com.wongcu.config;

import org.springframework.amqp.core.*;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.rabbit.listener.SimpleMessageListenerContainer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 *
 * @author WongCU
 * @date 2018/4/2
 */
@Configuration
public class MqConfiguration {

    @Bean
    public DirectExchange directExchange() {
        return new DirectExchange("directExchange");
    }

    @Bean
    public Queue imCcMessageQueue() {
        return new Queue("imCcMessageQueue", true);
    }

    @Bean
    public Binding chatLogBinding() {
        return BindingBuilder.bind(this.imCcMessageQueue())
                .to(this.directExchange())
                .with("imCcMessage");
    }
}
