package com.example.profilemicroservice.domain.modal;

public class User {
    private Long id;    
	private String name;
	private String email;
	private Long createdAt;
	private Long updatedAt;

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getEmail() {
        return email;
    }

    public Long getCreatedAt() {
        return createdAt;
    }

    public Long getUpdated() {
        return updatedAt;
    }
}
