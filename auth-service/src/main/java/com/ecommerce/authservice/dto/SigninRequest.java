package com.ecommerce.authservice.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class SigninRequest {
    @NotBlank(message = "enter username or email address, can't be empty")
    private String username;
    @NotBlank(message="enter password, can't be empty")
    private String password;
}
