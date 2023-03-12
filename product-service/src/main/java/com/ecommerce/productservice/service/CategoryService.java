package com.ecommerce.productservice.service;

import com.ecommerce.productservice.model.Category;
import com.ecommerce.productservice.repository.CategoryRepository;
import com.netflix.discovery.converters.Auto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CategoryService {
    @Autowired
    private CategoryRepository categoryRepository;

    public Optional<Category> getCategory(Long categoryId) {
        return categoryRepository.findById(categoryId);
    }
    public Optional<Category> getCategory(String categoryName) {
        return categoryRepository.findByName(categoryName);
    }
    public List<Category> getCategories(String categoryName) {
        if (categoryName !=null)
            return categoryRepository.findAllByName(categoryName);
        return categoryRepository.findAll();
    }
    public boolean deleteCategory(Long categoryId){
        if(categoryRepository.findById(categoryId).isEmpty())
            return false;
        categoryRepository.deleteById(categoryId);
        return true;
    }

    public Category addCategory(Category category){
        return categoryRepository.save(category);
    }
}
