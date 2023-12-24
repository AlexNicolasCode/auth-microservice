package com.ms.user.domain.model;

import java.util.regex.Pattern;

import com.ms.user.domain.exception.InvalidEmailException;

public final class Email {
    private String value;

    public Email(String value) throws InvalidEmailException, Exception {
        boolean isEmailFormat = Pattern.compile("^(.+)@(\\S+)$")
            .matcher(value)
            .matches();
        if (!isEmailFormat){
            throw new InvalidEmailException();
        }
        this.value = value;
    }

    public String getValue() {
        return value;
    }
}