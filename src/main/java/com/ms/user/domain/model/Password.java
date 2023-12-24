package com.ms.user.domain.model;

import com.ms.user.domain.exception.InvalidPasswordLength;

public final class Password {
    private String value;

    public Password(String value) throws InvalidPasswordLength, Exception {
        if (value.length() < 10){
            throw new InvalidPasswordLength();
        }
        this.value = value;
    }

    public String getValue() {
        return value;
    }
}
