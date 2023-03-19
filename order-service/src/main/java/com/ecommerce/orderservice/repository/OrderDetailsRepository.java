package com.ecommerce.orderservice.repository;

import com.ecommerce.orderservice.model.OrderDetails;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface OrderDetailsRepository extends JpaRepository<OrderDetails,Long> {
    List<OrderDetails> findAllByUserId(String userId);
}
