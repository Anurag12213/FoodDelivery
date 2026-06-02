package com.anurag.fooddelivery.entity;

import jakarta.persistence.*;

@Entity
public class OrderItem {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @ManyToOne
    @JoinColumn(name="order_id")
    private CustomerOrder order;

    @ManyToOne
    @JoinColumn(name="food_item_id")
    private FoodItem foodItem;

    private int quantity;

    private double price;
}
}
