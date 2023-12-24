package com.ms.user.data.protocol;

import com.ms.user.domain.model.Email;

public interface SendEmail {
    void send(String userName, Email userEmail, String subject, String text);
}
