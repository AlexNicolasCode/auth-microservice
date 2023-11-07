package com.ms.user.presentation.protocol;

public interface Validator<T> {
    Error validate(T params);
}
