package com.ecommerce.productservice.service;

import com.ecommerce.productservice.exception.ResourceNotFoundException;
import com.ecommerce.productservice.model.Category;
import com.ecommerce.productservice.repository.CategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CategoryService {
    @Autowired
    private CategoryRepository categoryRepository;

    public Category getCategory(Long categoryId) {
        Optional<Category> category =categoryRepository.findById(categoryId);
        if(category.isEmpty())
            throw new ResourceNotFoundException("Category not found with id: "+ categoryId);
        return category.get();
    }
    public Category getCategory(String categoryName) {
        Optional<Category> category = categoryRepository.findByName(categoryName);
        if(category.isEmpty())
            throw new ResourceNotFoundException("Category not found with name: "+ categoryName);
        return category.get();
    }
    public List<Category> getCategories(String categoryName) {
        if (categoryName !=null)
            return categoryRepository.findAllByName(categoryName);
        return categoryRepository.findAll();
    }
    public void deleteCategory(Long categoryId){
        if(categoryRepository.findById(categoryId).isEmpty())
            throw new ResourceNotFoundException("Category not found with id: "+ categoryId);
        categoryRepository.deleteById(categoryId);
    }

    public Category addCategory(Category category){
        return categoryRepository.save(category);
    }
}
