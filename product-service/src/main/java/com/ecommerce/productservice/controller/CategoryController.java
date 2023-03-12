package com.ecommerce.productservice.controller;

import com.ecommerce.productservice.utility.ApiResponse;
import com.ecommerce.productservice.utility.HeaderGenerator;
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
            return new ResponseEntity<>(new ApiResponse("Succeed",categories),headerGenerator.getHeadersForSuccessGetMethod(), HttpStatus.OK);
        }
        return new ResponseEntity<>(new ApiResponse("Not Found"),headerGenerator.getHeadersForError(),HttpStatus.NOT_FOUND);
    }
    @GetMapping("/{id}")
    public ResponseEntity<?> getCategory(@PathVariable Long id){
        Optional<Category> category = categoryService.getCategory(id);
        return new ResponseEntity<>(new ApiResponse("Succeed", category.get()),headerGenerator.getHeadersForSuccessGetMethod(), HttpStatus.OK);
    }

}
