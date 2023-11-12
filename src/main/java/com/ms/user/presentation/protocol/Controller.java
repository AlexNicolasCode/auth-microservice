package com.ms.user.presentation.protocol;

public interface Controller<T, E, R> {
    R handle (T params, E error);
}
