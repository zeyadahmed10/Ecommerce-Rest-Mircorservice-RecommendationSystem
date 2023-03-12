package com.ecommerce.productservice.repository;

import com.ecommerce.productservice.model.Category;
import com.ecommerce.productservice.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {
    Optional<Product> findById(Long id);
    Optional<Product> findByName(String name);
    List<Product> findAllByName(String name);
    List<Product> findAllByCategory(Category category);

}
