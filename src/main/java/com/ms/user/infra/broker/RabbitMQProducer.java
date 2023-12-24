package com.ms.user.infra.broker;

import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import com.ms.user.data.protocol.SendEmail;
import com.ms.user.domain.model.Email;
import com.ms.user.presentation.dto.EmailDto;

@Component
public class RabbitMQProducer implements SendEmail {

    final RabbitTemplate rabbitTemplate;

    public RabbitMQProducer(RabbitTemplate rabbitTemplate) {
        this.rabbitTemplate = rabbitTemplate;
    }

    @Value(value = "${broker.queue.email.name}")
    private String routingKey;

    public void send(String userName, Email userEmail, String subject, String text) {
        EmailDto emailDto = new EmailDto();
        emailDto.setEmailTo(userName);
        emailDto.setSubject(subject);
        emailDto.setText(userEmail + text);
        rabbitTemplate.convertAndSend("", routingKey, emailDto);
    }
}