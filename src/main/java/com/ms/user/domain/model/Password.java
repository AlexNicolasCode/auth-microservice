package com.ms.user.domain.model;

import com.ms.user.domain.exception.InvalidEmailException;

public final class Password {
    private String value;

    public Password(String value) throws InvalidEmailException, Exception {
        if (value.length() < 10){
            throw new InvalidEmailException();
        }
        this.value = value;
    }
}
