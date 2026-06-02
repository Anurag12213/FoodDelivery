package com.anurag.fooddelivery.entity;

import jakarta.persistence.*;

import java.time.LocalDateTime;
import java.util.List;
@Entity
public class CustomerOrder {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @ManyToOne
    @JoinColumn(name="customer_id")
    private User customer;
    @ManyToOne
    @JoinColumn(name="restaurant_id")
    private Restaurant restaurant;

    private double totalAmount;
    @Enumerated(EnumType.STRING)
    private  OrderStatus status;
    private LocalDateTime createdAt;
    @OneToMany(mappedBy = "order")
    private List<OrderItem> items;
    private double price;
}