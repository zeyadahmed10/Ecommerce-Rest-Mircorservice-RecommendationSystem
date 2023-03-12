package com.ecommerce.productservice.utility;

import lombok.Data;

import java.time.OffsetDateTime;

@Data
public class ApiResponse {
    private String message;
    private Object data;
    private OffsetDateTime time;

    public ApiResponse(String message, Object data) {
        this.message = message;
        this.data = data;
        this.time = java.time.OffsetDateTime.now();
    }
    public ApiResponse(String message){
        this.message = message;
        this.time = java.time.OffsetDateTime.now();
    }
}
