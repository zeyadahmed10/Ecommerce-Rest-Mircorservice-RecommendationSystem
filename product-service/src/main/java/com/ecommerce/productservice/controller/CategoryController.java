package com.ecommerce.productservice.controller;

import com.ecommerce.productservice.http.header.HeaderGenerator;
import com.ecommerce.productservice.model.Category;
import com.ecommerce.productservice.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/v1/category")
public class CategoryController {
    @Autowired
    private CategoryService categoryService;
    @Autowired
    private HeaderGenerator headerGenerator;
    @GetMapping
    public ResponseEntity<?> getCategories(@RequestParam(name="name",required = false) String name) {
        List<Category> categories = categoryService.getCategories(name);
        if(!categories.isEmpty()){
            return new ResponseEntity<>(categories,headerGenerator.getHeadersForSuccessGetMethod(), HttpStatus.OK);
        }
        return new ResponseEntity<>(headerGenerator.getHeadersForError(),HttpStatus.NOT_FOUND);
    }
    @GetMapping("/{id}")
    public ResponseEntity<?> getCategory(@PathVariable Long id){
        Optional<Category> category = categoryService.getCategory(id);
        if(category.isPresent()){
            return new ResponseEntity<>(category,headerGenerator.getHeadersForSuccessGetMethod(), HttpStatus.OK);
        }
        return new ResponseEntity<>(headerGenerator.getHeadersForError(),HttpStatus.NOT_FOUND);
    }

}
