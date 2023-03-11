package com.ecommerce.productservice.repository;

import com.ecommerce.productservice.model.Category;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface CategoryRepository extends JpaRepository<Long, Category> {
    Optional<Category> findById(Long id);
    Optional<Category> findByName(String name);
}
