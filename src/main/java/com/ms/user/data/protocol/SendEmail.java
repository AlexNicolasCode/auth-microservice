package com.ms.user.data.protocol;

import com.ms.user.domain.model.User;

public interface SendEmail {
    void send(User user, String subject, String text);
}
