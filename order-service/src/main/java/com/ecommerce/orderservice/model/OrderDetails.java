package com.ecommerce.orderservice.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.OffsetDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class OrderDetails {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;
    private Double price;
    private String address;
    private String phoneNumber;
    private String userId;
    private OffsetDateTime time;
    @OneToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinColumn(name ="order_id")
    private List<Order> orders;


    public OrderDetails(Double price, String address){
        this.price = price;
        this.address = address;
        this.time = OffsetDateTime.now();
    }

    public void setTime() {
        this.time = OffsetDateTime.now();
    }
}
