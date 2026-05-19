package com.anurag.fooddelivery.service;

import com.anurag.fooddelivery.entity.FoodItem;
import com.anurag.fooddelivery.entity.Restaurant;
import com.anurag.fooddelivery.repository.FoodItemRepository;
import com.anurag.fooddelivery.repository.RestaurantRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class FoodItemService {
    @Autowired
    FoodItemRepository repo;
    @Autowired
    RestaurantRepository restaurantrepo;

    //createFoodForRestaurant
    public FoodItem createFoodForRestaurant(FoodItem food,int id){

        food.setRestaurant(restaurantrepo.findById(id).orElse(null));
        return repo.save(food);

    }
    //create
    public FoodItem createFood(FoodItem food){
        return repo.save(food);
    }
    //getAll
    public List<FoodItem> getAllFood(){
        return repo.findAll();
    }
    //getById
    public FoodItem getById(int id){
        return repo.findById(id).orElse(null);
    }
    //update
    public FoodItem update(int id,FoodItem food){
        food.setId(id);
        return repo.save(food);
    }
    //delete
    public String delete(int id){
        repo.deleteById(id);
        return "Food Deleted";
    }


}
