package com.ecommerce.productservice.controller;

import com.ecommerce.productservice.http.header.HeaderGenerator;
import com.ecommerce.productservice.model.Category;
import com.ecommerce.productservice.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/v1/category")
public class CategoryController {
    @Autowired
    private CategoryService categoryService;
    @Autowired
    private HeaderGenerator headerGenerator;
    @GetMapping
    public ResponseEntity<?> getCategories() {
        List<Category> categories = categoryService.getCategories();
        if(!categories.isEmpty()){
            return new ResponseEntity<>(categories,headerGenerator.getHeadersForSuccessGetMethod(), HttpStatus.OK);
        }
        return new ResponseEntity<>(headerGenerator.getHeadersForError(),HttpStatus.NOT_FOUND);
    }
}
