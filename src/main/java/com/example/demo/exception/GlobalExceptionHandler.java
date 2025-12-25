package com.example.demo.exception;

import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.Map;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(RuntimeException.class)
    public Map<String, String> handleRuntimeException(RuntimeException ex) {
        return Map.of(
                "error", ex.getMessage() == null ? "Runtime error" : ex.getMessage()
        );
    }

    @ExceptionHandler(Exception.class)
    public Map<String, String> handleException(Exception ex) {
        return Map.of(
                "error", "Unexpected error"
        );
    }
}
