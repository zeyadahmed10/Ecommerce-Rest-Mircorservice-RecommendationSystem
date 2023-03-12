package com.ecommerce.productservice.controller;

import com.ecommerce.productservice.dto.ProductDto;
import com.ecommerce.productservice.http.header.HeaderGenerator;
import com.ecommerce.productservice.model.Product;
import com.ecommerce.productservice.service.ProductService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/api/v1/admin/product")
public class AdminProductController {
    @Autowired
    ProductService productService;
    @Autowired
    HeaderGenerator headerGenerator;

    @PostMapping
    public ResponseEntity<?> addProduct(@Valid @RequestBody ProductDto product, HttpServletRequest request) throws Exception {
        Product savedProduct = productService.addProduct(product);
        return new ResponseEntity<>(savedProduct,headerGenerator.getHeadersForSuccessPostMethod(request,savedProduct.getId())
                , HttpStatus.CREATED);
    }
    @DeleteMapping("{id}")
    public ResponseEntity<?> deleteProduct(@PathVariable Long id) {
        if(productService.deleteProduct(id)){
            return new ResponseEntity<>(headerGenerator.getHeadersForSuccessGetMethod(), HttpStatus.OK);
        }
        return new ResponseEntity<>(headerGenerator.getHeadersForError(), HttpStatus.NOT_FOUND);
    }
}
