/*
 * Copyright (C) 2019 Fastjrun, Inc. All Rights Reserved.
 */
package com.fastjrun.share.demo.mock;

import org.mockito.Mockito;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RabbitTemplateMockFactory {

    @Bean("rabbitTemplate")
    public RabbitTemplate getRabbitTemplate() {
        ConnectionFactory connectionFactory = Mockito.mock(ConnectionFactory.class);
        RabbitTemplate rabbitTemplate = new RabbitTemplate(connectionFactory);
        rabbitTemplate = Mockito.mock(RabbitTemplate.class);
        return rabbitTemplate;

    }
}
