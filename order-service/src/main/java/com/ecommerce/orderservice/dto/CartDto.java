package com.ecommerce.orderservice.dto;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CartDto {
    @NotBlank(message = "must enter product name")
    private String productName;
    @NotNull
    @Min(value = 0L, message = "The value must be positive")
    private Integer quantity;
}
