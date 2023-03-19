package com.ecommerce.orderservice.controller;

import com.ecommerce.orderservice.model.OrderDetails;
import com.ecommerce.orderservice.service.OrderDetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/order")
public class OrderDetailsController {

    @Autowired
    private OrderDetailsService orderDetailService;

    @GetMapping
    List<OrderDetails> getOrderDetails(@AuthenticationPrincipal Jwt jwt){
        return orderDetailService.getOrderDetailsList(jwt.getSubject());
    }
    @GetMapping("/{id}")
    OrderDetails getOrderDetails(@PathVariable Long orderId){
        return orderDetailService.getOrderDetails(orderId);
    }
}
