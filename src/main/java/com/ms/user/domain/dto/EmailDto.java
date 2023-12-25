package com.ms.user.domain.dto;

import com.ms.user.domain.model.Email;

public class EmailDto {
    private Email emailTo;
    private String subject;
    private String text;

    public EmailDto(Email emailTo, String subject, String text) {
        this.emailTo = emailTo;
        this.subject = subject;
        this.text = text;
    }

    public Email getEmailTo() {
        return emailTo;
    }

    public String getSubject() {
        return subject;
    }

    public String getText() {
        return text;
    }
}