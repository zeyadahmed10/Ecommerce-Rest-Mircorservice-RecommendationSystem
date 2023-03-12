package com.ecommerce.productservice.controller;

import com.ecommerce.productservice.utility.HeaderGenerator;
import com.ecommerce.productservice.model.Product;
import com.ecommerce.productservice.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
@RestController
@RequestMapping("/api/v1/product")
public class ProductController {
    @Autowired
    private ProductService productService;
    @Autowired
    private HeaderGenerator headerGenerator;
    @GetMapping
    public ResponseEntity<?> getCategories(@RequestParam(name="name",required = false) String name) {
        List<Product> products = productService.getProducts(name);
        if(!products.isEmpty()){
            return new ResponseEntity<>(products,headerGenerator.getHeadersForSuccessGetMethod(), HttpStatus.OK);
        }
        return new ResponseEntity<>(headerGenerator.getHeadersForError(),HttpStatus.NOT_FOUND);
    }
    @GetMapping(params = "category")
    public ResponseEntity<?> getProductByCategory(@RequestParam ("category") String category){
        List<Product> products = productService.getProductByCategory(category);
        if(products !=null || !products.isEmpty()){
            return new ResponseEntity<>(products,headerGenerator.getHeadersForSuccessGetMethod(), HttpStatus.OK);
        }
        return new ResponseEntity<>(headerGenerator.getHeadersForError(),HttpStatus.NOT_FOUND);
    }
    @GetMapping("/{id}")
    public ResponseEntity<?> getProduct(@PathVariable Long id){
        Optional<Product> product = productService.getProduct(id);
        if(product.isPresent()){
            return new ResponseEntity<>(product,headerGenerator.getHeadersForSuccessGetMethod(), HttpStatus.OK);
        }
        return new ResponseEntity<>(headerGenerator.getHeadersForError(),HttpStatus.NOT_FOUND);
    }
}
