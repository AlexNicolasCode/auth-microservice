package com.ms.user.data.usecase;

import com.ms.user.data.protocol.SendEmail;
import com.ms.user.domain.model.Email;
import com.ms.user.domain.usecase.SendWelcomeEmail;

public class RemoteSendWelcomeEmail implements SendWelcomeEmail {
    public RemoteSendWelcomeEmail(SendEmail sendEmail) {
        this.sendEmail = sendEmail;
    }

    private SendEmail sendEmail;

    @Override
    public void sendEmail(String userName, Email userEmail) {
        sendEmail.send(userName, userEmail, "Bem-vindo(A), " + userName, "Testando!");
    }
}
