package com.anurag.fooddelivery.repository;

import com.anurag.fooddelivery.entity.CustomerOrder;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CustomerOrderRepository
        extends JpaRepository<CustomerOrder,Integer> {

    List<CustomerOrder> findByCustomerId(int customerId);

    List<CustomerOrder> findByRestaurantId(int restaurantId);
}