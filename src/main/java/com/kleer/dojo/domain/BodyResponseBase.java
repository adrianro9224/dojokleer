package com.kleer.dojo.domain;

import com.fasterxml.jackson.annotation.JsonProperty;
import org.springframework.validation.ObjectError;

import java.util.Collection;

public class BodyResponseBase<T> {
    @JsonProperty("data")
    private T data;

    @JsonProperty("message")
    private String message;

    @JsonProperty("errors")
    private Collection<ObjectError> errors;

    public BodyResponseBase() {
    }

    public BodyResponseBase(T data, String message) {
        this.data = data;
        this.message = message;
    }

    public BodyResponseBase(T data, String message, Collection<ObjectError> errors) {
        this.data = data;
        this.message = message;
        this.errors = errors;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Collection<ObjectError> getErrors() {
        return errors;
    }

    public void setErrors(Collection<ObjectError> errors) {
        this.errors = errors;
    }
}
