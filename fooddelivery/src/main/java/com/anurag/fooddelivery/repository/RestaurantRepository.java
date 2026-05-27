package com.anurag.fooddelivery.repository;

import com.anurag.fooddelivery.entity.Restaurant;
import org.springframework.data.jpa.repository.JpaRepository;


import java.util.List;

public interface RestaurantRepository extends JpaRepository<Restaurant,Integer> {
    List<Restaurant> findByIsActiveTrue();

    Restaurant findByIdAndIsActiveTrue(int id);
}
