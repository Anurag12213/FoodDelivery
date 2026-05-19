package com.anurag.fooddelivery.repository;

import com.anurag.fooddelivery.entity.FoodItem;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FoodItemRepository extends JpaRepository<FoodItem,Integer> {
}
