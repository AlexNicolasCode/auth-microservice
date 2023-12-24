package com.ms.user.domain.model;

public class User {
	private Long id;
	private String name;
    private Email email;
    private Password password;

    public Long getId() {
        return this.id;
    }

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Email getEmail() {
        return this.email;
    }

    public void setEmail(Email email) {
        this.email = email;
    }

    public Password getPassword() {
        return this.password;
    }

    public void setPassword(Password password) {
        this.password = password;
    }
}
