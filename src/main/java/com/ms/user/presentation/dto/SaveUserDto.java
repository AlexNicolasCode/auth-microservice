package com.ms.user.presentation.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public class SaveUserDto {
    public SaveUserDto(
        String name,
        String email,
        String password
    ) {
        this.name = name;
        this.email = email;
        this.password = password;
    }

    @NotBlank(message = "Please, check name field")
    @NotNull(message = "Please, check name field")
    private String name;

    @Email(message = "Please, use a valid email")
    private String email;

    @NotBlank(message = "Please, check password field")
    @NotNull(message = "Please, check password field")
    private String password;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
