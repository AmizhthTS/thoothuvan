package com.smsapi.model;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.http.HttpStatus;

import lombok.Data;

@Data
public class ErrorModel {

    private int status ;
    
    private String error;

    private LocalDateTime timestamp;

    private String message;

    private List<ErrorField> errors;

    public ErrorModel(HttpStatus httpStatus, String message, List<ErrorField> errors) {
    	this.status=httpStatus.value();
    	this.error=httpStatus.name();
        this.timestamp = LocalDateTime.now();
        this.message = message;
        this.errors = errors;
    }


}