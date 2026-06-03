package com.anurag.fooddelivery.repository;

import com.anurag.fooddelivery.entity.OrderItem;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderItemRepository
        extends JpaRepository<OrderItem,Integer> {
}