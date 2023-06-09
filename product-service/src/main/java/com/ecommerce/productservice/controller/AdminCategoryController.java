package com.ecommerce.productservice.controller;

import com.ecommerce.productservice.utility.HeaderGenerator;
import com.ecommerce.productservice.model.Category;
import com.ecommerce.productservice.service.CategoryService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/admin/category")
@PreAuthorize("hasAuthority('ROLE_admin')")
public class AdminCategoryController {
    @Autowired
    CategoryService categoryService;
    @Autowired
    HeaderGenerator headerGenerator;
    @PostMapping
    public ResponseEntity<?> addCategory(@Valid @RequestBody Category category, HttpServletRequest request) {
        Category savedCategory = categoryService.addCategory(category);
        return new ResponseEntity<>(savedCategory,headerGenerator.getHeadersForSuccessPostMethod(request,savedCategory.getId())
                , HttpStatus.CREATED);
    }
    @DeleteMapping("{id}")
    public ResponseEntity<?> deleteCategory(@PathVariable Long id) {
        categoryService.deleteCategory(id);
        return new ResponseEntity<>(headerGenerator.getHeadersForSuccessGetMethod(), HttpStatus.OK);
    }
}
