package com.ecommerce.productservice.controller;

import com.ecommerce.productservice.model.Category;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/category")
public class CategoryController {
    @GetMapping
    public String getCategories() {
        return "Hell yeah";
    }
}
