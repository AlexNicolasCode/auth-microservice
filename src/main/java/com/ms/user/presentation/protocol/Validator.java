package com.ms.user.presentation.protocol;

public interface Validator<T> {
    Object validate(T params);
}
