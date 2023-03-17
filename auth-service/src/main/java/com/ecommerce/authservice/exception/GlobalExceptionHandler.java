package com.ecommerce.authservice.exception;

import com.ecommerce.authservice.utility.ApiResponse;
import com.ecommerce.authservice.utility.HeaderGenerator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {
    @Autowired
    HeaderGenerator headerGenerator;
    @ExceptionHandler(value = {RuntimeException.class})
    protected ResponseEntity<?> handleConflict(RuntimeException ex) {
        return new ResponseEntity<ApiResponse>(new ApiResponse(ex.getMessage() ),headerGenerator.getHeadersForError(), HttpStatus.BAD_REQUEST);
    }
}
