package com.linkedin.Post_Service.Exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;

import java.time.LocalDateTime;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<APIResponse> resourceNotFoundExceptionHandler(ResourceNotFoundException ex, WebRequest request){
        APIResponse apiResponse =
                new APIResponse(LocalDateTime.now(),
                        ex.getMessage(),
                        HttpStatus.NOT_FOUND,
                        request.getDescription(false));
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(apiResponse);
    }

    @ExceptionHandler(BadRequestException.class)
    public ResponseEntity<APIResponse> badRequestExceptionHandler(BadRequestException ex, WebRequest request){
        APIResponse apiResponse =
                new APIResponse(LocalDateTime.now(),
                        ex.getMessage(),
                        HttpStatus.BAD_REQUEST,
                        request.getDescription(false));
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(apiResponse);
    }


    @ExceptionHandler(RuntimeException.class)
    public ResponseEntity<APIResponse> runtimeExceptionHandler(RuntimeException ex, WebRequest request){
        APIResponse apiResponse =
                new APIResponse(LocalDateTime.now(),
                        ex.getMessage(),
                        HttpStatus.INTERNAL_SERVER_ERROR,
                        request.getDescription(false));
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(apiResponse);
    }
}
