package com.ms.user.presentation.protocol;

import org.springframework.http.ResponseEntity;

public interface Controller<T, R> {
    ResponseEntity<R> handle (T params);
}
