package com.ecommerce.productservice.service;

import com.ecommerce.productservice.dto.ProductDto;
import com.ecommerce.productservice.exception.ResourceNotFoundException;
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

    public Product getProduct(Long productId){

        Optional<Product> product = productRepository.findById(productId);
        if(product.isEmpty())
            throw new ResourceNotFoundException("Product not found with id " + productId);
        return product.get();
    }
    public List<Product> getProducts(String name){
        if(name !=null){
            return productRepository.findAllByName(name);
        }
        return productRepository.findAll();

    }
    public List<Product> getProductByCategory(String categoryName){
        Category category = categoryService.getCategory(categoryName);
        return productRepository.findAllByCategory(category);
    }
    public Product addProduct(ProductDto productDto){
        Category category = categoryService.getCategory(productDto.getCategoryId());
        return productRepository.save(productDto.productBuilder(category));
    }
    public void deleteProduct(Long productId){
        productRepository.deleteById(productId);
    }
}
