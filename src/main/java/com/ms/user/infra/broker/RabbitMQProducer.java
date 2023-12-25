package com.ms.user.infra.broker;

import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import com.ms.user.data.protocol.SendEmail;
import com.ms.user.domain.dto.EmailDto;
import com.ms.user.domain.model.Email;

@Component
public class RabbitMQProducer implements SendEmail {

    final RabbitTemplate rabbitTemplate;

    public RabbitMQProducer(RabbitTemplate rabbitTemplate) {
        this.rabbitTemplate = rabbitTemplate;
    }

    @Value(value = "${broker.queue.email.name}")
    private String routingKey;

    public void send(EmailDto emailDto) {
        rabbitTemplate.convertAndSend("", routingKey, emailDto);
    }
}