package com.ms.user.presentation.protocol;

import org.springframework.http.ResponseEntity;

public interface Controller<T, E, R> {
    ResponseEntity<R> handle (T params, E error);
}
