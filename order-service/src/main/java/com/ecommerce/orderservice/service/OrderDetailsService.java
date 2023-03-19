package com.ecommerce.orderservice.service;

import com.ecommerce.orderservice.exception.ResourceNotFoundException;
import com.ecommerce.orderservice.model.OrderDetails;
import com.ecommerce.orderservice.repository.OrderDetailsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OrderDetailsService {
    @Autowired
    private OrderDetailsRepository orderDetailsRepository;

    public List<OrderDetails> getOrderDetailsList(String userId) {
        return orderDetailsRepository.findAllByUserId(userId);
    }
    public OrderDetails getOrderDetails(Long orderId) {
        var order = orderDetailsRepository.findById(orderId);
        if(order.isEmpty())
            throw new ResourceNotFoundException("Order not found with id " + orderId);
        return order.get();
    }
}
