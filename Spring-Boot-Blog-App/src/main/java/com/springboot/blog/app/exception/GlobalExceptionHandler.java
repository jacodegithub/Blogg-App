package com.springboot.blog.app.exception;

import java.util.HashMap;
import java.util.Map;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationCredentialsNotFoundException;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.validation.BindException;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;


@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(ResourceNotFoundException.class)
    public String resourceNotFoundException(ResourceNotFoundException ex) {
        String message = ex.getMessage();
        return message;
    }

    // @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(NullPointerException.class) 
    public String nullPointerException(NullPointerException ex) {
        String message = ex.getMessage();
        return message;
    }

    @ExceptionHandler( ApiError.class )
    @ResponseStatus(HttpStatus.UNAUTHORIZED)
    public ResponseEntity<ApiError> handleAll(ApiError ex) {
        ApiError apiError = new ApiError(ex.getLocalizedMessage());
        return new ResponseEntity<ApiError>(apiError, new HttpHeaders(), HttpStatus.BAD_REQUEST); 
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<Map<String, String>> handleMethodNotValidException(MethodArgumentNotValidException ex) {
        Map<String, String> map = new HashMap<>();
        ex.getBindingResult().getAllErrors().forEach(er -> {
            String fieldName = ((FieldError) er).getField();
            String message = er.getDefaultMessage();
            map.put(fieldName, message);
        });
        return new ResponseEntity<Map<String, String>>(map, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(BadCredentialsException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ErrorResponse handleException(BadCredentialsException ex) {
        ErrorResponse error = new ErrorResponse(ex.getMessage(), "Invalid Username or password!!");
        return error;
    }

    // @ExceptionHandler(AuthenticationCredentialsNotFoundException.class)
    // @ResponseStatus(HttpStatus.BAD_REQUEST)
    // public ErrorResponse handleAuthenticationCredentialNotFoundException(AuthenticationCredentialsNotFoundException ex) {
    //     ErrorResponse error = new ErrorResponse(ex.getMessage(), "Login time limit exceeded!!");
    //     return error;
    // }
}
