package com.ms.user.domain.dto;

import com.ms.user.domain.model.Email;
import com.ms.user.domain.model.Password;

public class AuthenticationDto {
    private Email email;
    private Password password;
    private String errorMessage;

    public AuthenticationDto(
        String email,
        String password
    ) {
        try {
            this.email = new Email(email);
            this.password = new Password(password);
        } catch (Exception error) {
            this.errorMessage = error.getMessage();
        }
    }

    public String getError() {
        return errorMessage;
    }
    
    public Email getEmail() {
        return email;
    }

    public Password getPassword() {
        return password;
    }
}
