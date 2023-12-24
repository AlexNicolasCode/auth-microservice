package com.ms.user.domain.usecase;

import com.ms.user.domain.model.Email;

public interface SendWelcomeEmail {
    void sendEmail(String userName, Email userEmail);
}
