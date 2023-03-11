package com.ecommerce.productservice.repository;

import com.ecommerce.productservice.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ProductRepository extends JpaRepository<Long, Product> {
    Optional<Product> findById(Long id);
    Optional<Product> findByName(String name);
}
