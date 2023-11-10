package com.ms.user.domain.mock;

import com.github.javafaker.Faker;
import com.ms.user.domain.model.User;

public class UserMock {

    private Faker faker = new Faker(); 
    
    public User build() {
        User user = new User();
        user.setName(faker.name().firstName());
        user.setEmail(faker.internet().emailAddress());
        user.setPassword(faker.internet().password());
        return user;
    } 
}
