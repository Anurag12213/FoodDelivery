package com.anurag.fooddelivery.service;

import com.anurag.fooddelivery.entity.FoodItem;
import com.anurag.fooddelivery.repository.RestaurantRepository;
import com.anurag.fooddelivery.entity.Restaurant;
import com.anurag.fooddelivery.dto.RestaurantDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RestaurantService {

    @Autowired
    RestaurantRepository repo;

    //createRestorent
    public Restaurant createRestaurant(Restaurant restaurant){
        return repo.save(restaurant);
    }

    //getAll
    public List<Restaurant> getAllRestaurant(){
        return repo.findAll();
    }

    //get By ID
    public Restaurant getById(int id){
        return repo.findById(id).orElse(null);
    }

    //update by id
    public Restaurant update(int id,Restaurant restaurant){
        restaurant.setId(id);
        return repo.save(restaurant);
    }

    //delete
    public String delete(int id){
        repo.deleteById(id);
        return "Restaurant Deleted";
    }

    //GetAllRestaurantFoods
    public List<FoodItem> getRestaurantFoods(int id){
        Restaurant restaurant=repo.findById(id).orElse(null);
        return restaurant.getFoods();
    }

    //getRestaurantDTO
    public RestaurantDTO getRestaurantDTO(int id){
        Restaurant restaurant=repo.findById(id).orElse(null);
        RestaurantDTO dto=new RestaurantDTO();
        dto.setId(restaurant.getId());
        dto.setName(restaurant.getName());
        dto.setLocation(restaurant.getLocation());
        return dto;
    }

}
