package com.anurag.fooddelivery.repository;

import com.anurag.fooddelivery.entity.FoodItem;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface FoodItemRepository
        extends JpaRepository<FoodItem,Integer> {

    List<FoodItem> findByIsActiveTrue();

    FoodItem findByIdAndIsActiveTrue(int id);
}