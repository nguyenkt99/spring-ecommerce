package com.example.springecommerce.exception;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;
import java.util.Map;

@Getter
@Setter
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ApiError {
    private int status;
    private String message;
    private long timestamp;
    private String path;
    private Map<String, String> validationErrors;

    public ApiError(int status, String message, String path) {
        this.timestamp = new Date().getTime();
        this.status = status;
        this.message = message;
        this.path = path;
    }
}
