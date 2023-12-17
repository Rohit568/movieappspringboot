package com.pack.gatewayservice.exception;

import java.io.IOException;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.reactive.result.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
public class CustomizedExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(RuntimeException.class)
    public ResponseEntity<Object> handleExceptions(Exception ex, WebRequest webRequest) {
        Map<String, String> response= new HashMap<>();
        response.put("date_time", "" +LocalDateTime.now());
        response.put("log", ex.getMessage());
        
        ResponseEntity<Object> entity = new ResponseEntity<>(response,HttpStatus.BAD_REQUEST);
        return entity;
    }

}

