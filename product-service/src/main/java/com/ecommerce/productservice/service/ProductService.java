package com.ecommerce.productservice.service;

import com.ecommerce.productservice.dto.ProductDto;
import com.ecommerce.productservice.model.Category;
import com.ecommerce.productservice.model.Product;
import com.ecommerce.productservice.repository.CategoryRepository;
import com.ecommerce.productservice.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProductService {
    @Autowired
    private ProductRepository productRepository;
    @Autowired
    private CategoryService categoryService;
    @Autowired
    private CategoryRepository categoryRepository;

    public Optional<Product> getProduct(Long productId){
        return productRepository.findById(productId);
    }
    public List<Product> getProducts(String name){
        if(name !=null){
            productRepository.findAllByName(name);
        }
        return productRepository.findAll();

    }
    public List<Product> getProductByCategory(String categoryName){
        Optional<Category> category = categoryService.getCategory(categoryName);
        if(category.isPresent()){
            return productRepository.findAllByCategory(category.get());
        }
        return null;
    }
    public Product addProduct(ProductDto productDto) throws Exception {
        Optional<Category> category = categoryService.getCategory(productDto.getCategoryId());
        if(category.isEmpty())
            throw new Exception("Category not found with id:  " + productDto.getCategoryId());
        return productRepository.save(productDto.productBuilder(category.get()));
    }
    public boolean deleteProduct(Long productId){
        if(productRepository.findById(productId).isEmpty())
            return false;
        productRepository.deleteById(productId);
        return true;
    }
}
