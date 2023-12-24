package com.ms.user.domain.dto;


import com.ms.user.domain.model.Email;
import com.ms.user.domain.model.Password;

public class SaveUserDto {
    private final String name;
    private final Email email;
    private final Password password;

    public SaveUserDto(
        String name,
        Email email,
        Password password
    ) {
        this.name = name;
        this.email = email;
        this.password = password;
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
