package com.ecommerce.orderservice.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class OrderDetailsDto {
    @NotBlank
    @Size(min=8, message = "Address can't be less than 8 characters")
    private String address;

    @NotNull
    @Pattern(regexp="(^$|[0-9]{10})")
    private String phoneNumber;

}
