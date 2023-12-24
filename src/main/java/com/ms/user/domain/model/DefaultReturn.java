package com.ms.user.domain.model;

public class DefaultReturn<T> {
    private String error;
    private T content;

    public DefaultReturn(String error, T content) {
        this.error = error;
        this.content = content;
    }

    public String getError() {
        return error;
    }

    public T getContent() {
        return content;
    }
}
