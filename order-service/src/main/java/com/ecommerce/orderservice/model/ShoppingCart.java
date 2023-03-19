package com.ecommerce.orderservice.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Table(name="shopping_cart")
@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class ShoppingCart {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;
    private String userId;
    private Long productId;
    private Integer quantity;
    private Double price;

    public void setProductId(Object productId) {
        this.productId = ((Number)productId).longValue();
    }

    public void setPrice(Object price) {
        this.price = calculatePrice(price);
    }
    protected Double calculatePrice(Object price) {
        return this.quantity * (Double) price;
    }
    protected Double calculatePrice(Double price) {
        return this.quantity * price;
    }
}
