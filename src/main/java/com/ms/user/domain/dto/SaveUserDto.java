package com.ms.user.domain.dto;

import com.ms.user.domain.model.Email;
import com.ms.user.domain.model.Password;

public class SaveUserDto {
    private String name;
    private Email email;
    private Password password;
    private String errorMessage;

    public SaveUserDto(
        String name,
        String email,
        String password
    ) {
        try {
            this.name = name;
            this.email = new Email(email);
            this.password = new Password(password);
        } catch (Exception error) {
            this.errorMessage = error.getMessage();
        }
    }

    public String getError() {
        return errorMessage;
    }

    public String getName() {
        return name;
    }

    public Email getEmail() {
        return email;
    }

    public Password getPassword() {
        return password;
    }
}
