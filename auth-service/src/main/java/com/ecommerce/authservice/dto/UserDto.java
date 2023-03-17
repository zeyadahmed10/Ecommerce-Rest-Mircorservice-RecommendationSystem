package com.ecommerce.authservice.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserDto {
    @NotBlank
    @Size(min=3, message = "username must be greater than 3 characters")
    private String username;
    @NotBlank
    @Email(message = "not a valid email address")
    @Size(min=10, message = "email address but be greater than 10 characters")
    private String email;
    @NotBlank
    @Size(min=3,max=32, message = "password must be greater than 8 characters and less than 32")
    private String password;
    @NotBlank
    @Size(min=3, message = "first name must be greater than 3 characters")
    private String firstName;
    @NotBlank
    @Size(min=3, message = "last name must be greater than 3 characters")
    private String lastName;
    private String role;
}
