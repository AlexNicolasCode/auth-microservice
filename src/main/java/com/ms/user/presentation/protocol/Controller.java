package com.ms.user.presentation.protocol;

public interface Controller<T, R> {
    R handle (T params);
}
