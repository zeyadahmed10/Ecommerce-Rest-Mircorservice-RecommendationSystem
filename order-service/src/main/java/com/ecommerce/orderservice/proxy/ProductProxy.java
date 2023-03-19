package com.ecommerce.orderservice.proxy;

import com.ecommerce.orderservice.utility.ApiResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@FeignClient(name="product-service")
public interface ProductProxy {
    @GetMapping("/api/v1/product")
    public ResponseEntity<ApiResponse> getProduct(@RequestParam(name="name",required = false) String name);
}
