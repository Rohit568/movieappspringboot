package com.pack.moviemicroservice.exception;
import java.io.IOException;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
public class CustomizedExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(IOException.class)
    public ResponseEntity<Object> handleExceptions(Exception ex, WebRequest webRequest) {
        Map<String, String> response= new HashMap<>();
        response.put("date_time", "" +LocalDateTime.now());
        response.put("log", ex.getMessage());
        
        ResponseEntity<Object> entity = new ResponseEntity<>(response,HttpStatus.FAILED_DEPENDENCY);
        return entity;
    }
   
    @ExceptionHandler(InterruptedException.class)
    public ResponseEntity<Object> handleinterruptExceptions(Exception ex, WebRequest webRequest) {
        Map<String, String> response= new HashMap<>();
        response.put("date_time", "" +LocalDateTime.now());
        response.put("log", ex.getMessage());
        
        ResponseEntity<Object> entity = new ResponseEntity<>(response,HttpStatus.EXPECTATION_FAILED);
        return entity;
    }
    
  
}
