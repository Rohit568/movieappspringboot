package com.pack.authmicroservice.exception;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
public class CustomizedExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(UsernameNotFoundException.class)
    public ResponseEntity<Object> handleExceptions(Exception ex, WebRequest webRequest) {
        Map<String, String> response= new HashMap<>();
        response.put("date_time", "" +LocalDateTime.now());
        response.put("log", ex.getMessage());
        
        ResponseEntity<Object> entity = new ResponseEntity<>(response,HttpStatus.NOT_FOUND);
        return entity;
    }
    @ExceptionHandler(UsernameOrPasswordInvalidException.class)
    public ResponseEntity<Object> handle2Exceptions(RuntimeException ex, WebRequest webRequest) {
        Map<String, String> response= new HashMap<>();
        response.put("date_time", "" +LocalDateTime.now());
        response.put("log", ex.getMessage());
        
        ResponseEntity<Object> entity = new ResponseEntity<>(response,HttpStatus.BAD_REQUEST);
        return entity;
    }
}
