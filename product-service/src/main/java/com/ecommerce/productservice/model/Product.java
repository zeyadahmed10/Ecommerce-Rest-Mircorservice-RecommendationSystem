package com.ecommerce.productservice.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.CascadeType;

import java.math.BigDecimal;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;
    @NotBlank
    @Size(min=3, max=100, message = "Product name must not be empty and no less than 3 characters")
    private String name;
    @NotBlank
    @Size(min=3, message = "Product description must not be empty and no less than 3 characters")
    private String description;
    @NotNull
    @Min(value = 0L, message = "The value must be positive")
    private Double price;
    @ManyToOne
    @JoinColumn(name = "category_id")
    private Category category;

    public Product(String name, String description, Category category, Double price) {
        this.name = name;
        this.description = description;
        this.price = price;
        this.category = category;
    }

    public Product(String name, String description, Double price) {
        this.name = name;
        this.description = description;
        this.price = price;
    }
}
