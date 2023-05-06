package com.ecommerce.orderservice.controller;

import com.ecommerce.orderservice.dto.CartDto;
import com.ecommerce.orderservice.dto.OrderDetailsDto;
import com.ecommerce.orderservice.exception.EmptyResourceException;
import com.ecommerce.orderservice.model.OrderDetails;
import com.ecommerce.orderservice.model.ShoppingCart;
import com.ecommerce.orderservice.service.CartService;
import com.ecommerce.orderservice.utility.ApiResponse;
import com.ecommerce.orderservice.utility.HeaderGenerator;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/cart")
public class CartController {
    @Autowired
    private HeaderGenerator headerGenerator;
    @Autowired
    private CartService cartService;
    @GetMapping
    public ResponseEntity<?> getCartItems(@AuthenticationPrincipal Jwt jwt,@RequestParam(name="name",required = false) String productName){
        List<ShoppingCart> cartItems =null;
        if(productName!=null)
            cartItems = cartService.getCartItems(jwt.getSubject(),productName);
        else
            cartItems = cartService.getCartItems(jwt.getSubject());
        if(cartItems.isEmpty())
            throw new EmptyResourceException("No cart items found for user: " + jwt.getClaims().get("given_name"));
        return new ResponseEntity<>(new ApiResponse("Succeed",cartItems),headerGenerator.getHeadersForSuccessGetMethod(), HttpStatus.OK);
    }
    @PostMapping
    public ResponseEntity<?> addCartItem(@AuthenticationPrincipal Jwt jwt, @RequestBody @Valid CartDto cartDto, HttpServletRequest request){
        ShoppingCart cart = cartService.addCartItem(jwt.getSubject(), cartDto);
        return new ResponseEntity<>(new ApiResponse("Succeed",cart),headerGenerator.getHeadersForSuccessPostMethod(request,cart.getId())
                , HttpStatus.CREATED);
    }
    @DeleteMapping("/{cartId}")
    public ResponseEntity<?> removeCartItem(@AuthenticationPrincipal Jwt jwt, @PathVariable Long cartId){
        cartService.deleteCartItem(cartId);
        return new ResponseEntity<>(new ApiResponse("Succeed"), headerGenerator.getHeadersForSuccessGetMethod(),
                HttpStatus.OK);
    }
    @PutMapping("/{cartId}")
    public ResponseEntity<?> updateCartItem(@AuthenticationPrincipal Jwt jwt, @PathVariable Long cartId, @RequestBody @Valid CartDto cartDto, HttpServletRequest request){
        ShoppingCart cart = cartService.updateCartItem(jwt.getSubject(), cartId,cartDto);
        return new ResponseEntity<>(new ApiResponse("Succeed",cart),headerGenerator.getHeadersForSuccessPostMethod(request,cart.getId())
                , HttpStatus.CREATED);
    }
    @PostMapping("/toOrder")
    public ResponseEntity<?> cartToOrder(@AuthenticationPrincipal Jwt jwt, @RequestBody @Valid OrderDetailsDto orderDetailsDto, HttpServletRequest request){
        OrderDetails orderDetails= cartService.makeOrder(jwt.getSubject(), orderDetailsDto);
        return new ResponseEntity<>(new ApiResponse("Succeed",orderDetails),headerGenerator.getHeadersForSuccessPostMethod(request,orderDetails.getId())
                , HttpStatus.CREATED);
    }
}
