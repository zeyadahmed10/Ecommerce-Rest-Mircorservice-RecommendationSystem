package com.ecommerce.productservice.dto;

import com.ecommerce.productservice.model.Category;
import com.ecommerce.productservice.model.Product;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class ProductDto {
    @NotBlank
    @Size(min=3, max=100, message = "Product name must not be empty and no less than 3 characters")
    private String name;
    @NotBlank
    @Size(min=3, message = "Product description must not be empty and no less than 3 characters")
    private String description;
    @NotNull
    @Min(value = 0L, message = "The value must be positive")
    private Double price;
    @NotNull
    private Long categoryId;

    public Product productBuilder(Category category){
        return new Product(this.name, this.description, category, this.price);
    }
}
