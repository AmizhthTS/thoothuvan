package com.smsapi.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import com.smsapi.model.ErrorField;
import com.smsapi.model.ErrorModel;
import com.smsapi.validation.ValidationException;


@RestControllerAdvice
public class RestExceptionHandler  extends ResponseEntityExceptionHandler {


    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex, HttpHeaders headers, HttpStatus status, WebRequest request) {
          

    	final List<ErrorField> errors = new ArrayList<ErrorField>();
    	
        for (final ObjectError error : ex.getBindingResult().getAllErrors()) {
        	
        	if(error instanceof FieldError) {
                errors.add(new ErrorField(((FieldError)error).getField(),error.getDefaultMessage(),(String)((FieldError) error).getRejectedValue()));

        	}else {
        		
                errors.add(new ErrorField(error.getObjectName(),error.getDefaultMessage(),""));

        	}
        }
        
        
		ErrorModel error1 = new ErrorModel(HttpStatus.BAD_REQUEST, "Validation Error",errors );

        return new ResponseEntity<>(error1, HttpStatus.BAD_REQUEST);
    }

    
    @ExceptionHandler(ValidationException.class)
    public ResponseEntity<Object> getValidationException(ValidationException ex){
    	
           return new ResponseEntity<>(ex.getErrormodel(), ex.getHttpstatus());
    }
    
    
}

