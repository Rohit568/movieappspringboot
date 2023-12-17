package com.pack.usermicroservice.exception;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import com.fasterxml.jackson.core.JsonParseException;

@ControllerAdvice
public class CustomizedExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(UserAlreadyExistsException.class)
    public ResponseEntity<Object> handleExceptions(Exception ex, WebRequest webRequest) {
        Map<String, String> response= new HashMap<>();
        response.put("date_time", "" +LocalDateTime.now());
        response.put("log", ex.getMessage());
        
        ResponseEntity<Object> entity = new ResponseEntity<>(response,HttpStatus.ALREADY_REPORTED);
        return entity;
    }
   
    @ExceptionHandler(JsonParseException.class)
    public ResponseEntity<Object> handleExceptions2(Exception ex, WebRequest webRequest) {
        Map<String, String> response= new HashMap<>();
        response.put("date_time", "" +LocalDateTime.now());
        response.put("log", ex.getMessage());
        
        ResponseEntity<Object> entity = new ResponseEntity<>(response,HttpStatus.FAILED_DEPENDENCY);
        return entity;
    }
   
   
    
  
}
