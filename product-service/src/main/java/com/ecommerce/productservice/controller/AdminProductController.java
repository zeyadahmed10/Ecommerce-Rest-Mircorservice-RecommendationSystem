package com.ecommerce.productservice.controller;

import com.ecommerce.productservice.dto.ProductDto;
import com.ecommerce.productservice.utility.ApiResponse;
import com.ecommerce.productservice.utility.HeaderGenerator;
import com.ecommerce.productservice.model.Product;
import com.ecommerce.productservice.service.ProductService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/admin/product")
@PreAuthorize("hasAuthority('admin')")
public class AdminProductController {
    @Autowired
    ProductService productService;
    @Autowired
    HeaderGenerator headerGenerator;

    @PostMapping
    public ResponseEntity<?> addProduct(@Valid @RequestBody ProductDto product, HttpServletRequest request) throws Exception {
        Product savedProduct = productService.addProduct(product);
        return new ResponseEntity<>(new ApiResponse("Succeed",savedProduct),headerGenerator.getHeadersForSuccessPostMethod(request,savedProduct.getId())
                , HttpStatus.CREATED);
    }
    @DeleteMapping("{id}")
    public ResponseEntity<?> deleteProduct(@PathVariable Long id) {
        productService.deleteProduct(id);
        return new ResponseEntity<>(new ApiResponse("Succeed"),headerGenerator.getHeadersForSuccessGetMethod(), HttpStatus.OK);

    }
}
