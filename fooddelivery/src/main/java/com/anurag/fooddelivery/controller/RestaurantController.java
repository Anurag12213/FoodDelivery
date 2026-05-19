package com.anurag.fooddelivery.controller;
import com.anurag.fooddelivery.entity.FoodItem;
import com.anurag.fooddelivery.entity.Restaurant;
import com.anurag.fooddelivery.service.RestaurantService;
import com.anurag.fooddelivery.dto.RestaurantDTO;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/restaurants")
public class RestaurantController {
    @Autowired
    RestaurantService service;



    @PostMapping
    public Restaurant createRestaurant(@Valid @RequestBody Restaurant restaurant){
        return service.createRestaurant(restaurant);
    }
    @GetMapping
    public List<Restaurant> getRestaurants(){
        return service.getAllRestaurant();
    }

    @GetMapping("/{id}")
    public Restaurant getRestaurantById(@PathVariable int id){
        return service.getById(id);
    }

    @PutMapping({"/{id}"})
    public Restaurant update(@PathVariable int id,@RequestBody Restaurant restaurant){
        return service.update(id,restaurant);
    }

    @DeleteMapping("/{id}")
    public String delete(@PathVariable int id){
        return service.delete(id);
    }

    @GetMapping("/{id}/foods")
    public List<FoodItem> getRestaurantFoods(@PathVariable int id){
       return service.getRestaurantFoods(id);
    }

    @GetMapping("/com/anurag/fooddelivery/dto/{id}")
    public RestaurantDTO getRestaurantDTO(@PathVariable int id){
        return service.getRestaurantDTO(id);
    }
}
