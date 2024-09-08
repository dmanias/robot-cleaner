package com.demo.robot_cleaner.exception;

import lombok.Getter;
import org.springframework.http.HttpStatus;
import java.util.Date;

@Getter
public class ErrorDetails {
    private final Date timestamp;
    private final HttpStatus status;
    private final String message;
    private final String details;

    public ErrorDetails(HttpStatus status, String message, String details) {
        this.timestamp = new Date();
        this.status = status;
        this.message = message;
        this.details = details;
    }
}