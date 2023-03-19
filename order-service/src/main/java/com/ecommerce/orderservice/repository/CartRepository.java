package com.ecommerce.orderservice.repository;

import com.ecommerce.orderservice.model.ShoppingCart;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
public interface CartRepository extends JpaRepository<ShoppingCart,Long> {
    List<ShoppingCart> findAllByUserId(String userId);
    List<ShoppingCart> findAllByUserIdAndProductId(String userId, long productId);
    void deleteAllByUserId(String userId);
}
