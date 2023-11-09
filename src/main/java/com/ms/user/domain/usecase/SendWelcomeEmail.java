package com.ms.user.domain.usecase;

import com.ms.user.domain.model.User;

public interface SendWelcomeEmail {
    void sendEmail(User user);
}
