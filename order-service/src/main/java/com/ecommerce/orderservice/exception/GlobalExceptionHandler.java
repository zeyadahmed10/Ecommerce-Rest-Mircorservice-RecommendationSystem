package com.ecommerce.orderservice.exception;

import com.ecommerce.orderservice.utility.ApiResponse;
import com.ecommerce.orderservice.utility.HeaderGenerator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {
    @Autowired
    HeaderGenerator headerGenerator;
    @ExceptionHandler(value = {EmptyResourceException.class})
    protected ResponseEntity<?> handleConflictCartOrder(RuntimeException ex) {
        return new ResponseEntity<ApiResponse>(new ApiResponse(ex.getMessage() ),headerGenerator.getHeadersForError(), HttpStatus.NOT_FOUND);
    }
    @ExceptionHandler(value = {ResourceNotFoundException.class})
    protected ResponseEntity<?> handleConflictProduct(RuntimeException ex) {
        return new ResponseEntity<ApiResponse>(new ApiResponse(ex.getMessage() ),headerGenerator.getHeadersForError(), HttpStatus.NOT_FOUND);
    }
}
