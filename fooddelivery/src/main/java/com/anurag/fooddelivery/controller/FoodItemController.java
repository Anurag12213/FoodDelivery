package com.anurag.fooddelivery.controller;

import com.anurag.fooddelivery.entity.FoodItem;
import com.anurag.fooddelivery.service.FoodItemService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/foods")
public class FoodItemController {
    @Autowired
    FoodItemService service;

    //createFoodForRestaurant
    @PostMapping("/restaurant/{restaurantId}")
    public FoodItem createFoodForRestaurant(@RequestBody FoodItem food,@PathVariable int restaurantId){
        return service.createFoodForRestaurant(food,restaurantId);
    }

    //getFoods
    @GetMapping
    public List<FoodItem> getAllFoods(){
        return service.getAllFood();
    }
    //getFoodBtId
    @GetMapping("/{id}")
    public FoodItem getFood(@PathVariable int id){
        return service.getById(id);
    }
    @PutMapping("/{id}")
    //updateFood
    public FoodItem update(@PathVariable int id,@RequestBody FoodItem newFood){
        return service.update(id,newFood);
    }
    //deleteFood
    @DeleteMapping("/{id}")
    public String delete(@PathVariable int id){
        return service.delete(id);
    }

}
