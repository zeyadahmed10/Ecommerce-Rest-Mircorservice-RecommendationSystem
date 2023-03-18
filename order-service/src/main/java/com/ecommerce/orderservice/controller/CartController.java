package com.ecommerce.orderservice.controller;

import com.ecommerce.orderservice.service.CartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.AuthenticatedPrincipal;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/v1/cart")
public class CartController {
    @Autowired
    private CartService cartService;

    public ResponseEntity<?> getCartItems(@AuthenticationPrincipal Jwt jwt){
        return null;
    }
    public ResponseEntity<?> addCartItem(@AuthenticationPrincipal Jwt jwt){
        return null;
    }
    public ResponseEntity<?> removeCartItem(@AuthenticationPrincipal Jwt jwt){
        return null;
    }
    public ResponseEntity<?> updateCartItem(@AuthenticationPrincipal Jwt jwt){
        return null;
    }
    public ResponseEntity<?> cartToOrder(@AuthenticationPrincipal Jwt jwt){
        return null;
    }
}
