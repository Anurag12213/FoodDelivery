package com.anurag.fooddelivery.service;

import com.anurag.fooddelivery.entity.FoodItem;
import com.anurag.fooddelivery.entity.Restaurant;
import com.anurag.fooddelivery.entity.User;
import com.anurag.fooddelivery.repository.FoodItemRepository;
import com.anurag.fooddelivery.repository.RestaurantRepository;
import com.anurag.fooddelivery.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@Service
public class FoodItemService {
    @Autowired
    FoodItemRepository repo;
    @Autowired
    RestaurantRepository restaurantRepo;
    @Autowired
    UserRepository userRepo;

    //createFoodForRestaurant
    public FoodItem createFoodForRestaurant(FoodItem food,int restaurantId){

        Restaurant restaurant=restaurantRepo.findByIdAndIsActiveTrue(restaurantId);
        if(restaurant==null){
            throw new ResponseStatusException(
                    HttpStatus.NOT_FOUND,"Restaurant Not Found"
            );
        }

        String email= SecurityContextHolder.getContext().getAuthentication().getName();
        User loggedInUser=userRepo.findByEmail(email);
        if(restaurant.getOwner().getId() != loggedInUser.getId()){
            throw new ResponseStatusException(
                    HttpStatus.FORBIDDEN,"Access Denied"
            );
        }
        food.setRestaurant(restaurant);
        return repo.save(food);
    }
    //getAll
    public List<FoodItem> getAllFood(){
        return repo.findByIsActiveTrue();
    }
    //getById
    public FoodItem getById(int id){

        FoodItem food =
                repo.findByIdAndIsActiveTrue(id);

        if(food == null){

            throw new ResponseStatusException(
                    HttpStatus.NOT_FOUND,
                    "Food Not Found"
            );
        }

        return food;
    }
    //update
    public FoodItem update(int id, FoodItem newFood){

        FoodItem existingFood =
                repo.findByIdAndIsActiveTrue(id);

        if(existingFood == null){

            throw new ResponseStatusException(
                    HttpStatus.NOT_FOUND,
                    "Food Not Found"
            );
        }

        String email =
                SecurityContextHolder
                        .getContext()
                        .getAuthentication()
                        .getName();

        User loggedInUser =
                userRepo.findByEmail(email);

        if(existingFood.getRestaurant()
                .getOwner()
                .getId()
                != loggedInUser.getId()){

            throw new ResponseStatusException(
                    HttpStatus.FORBIDDEN,
                    "Access Denied"
            );
        }

        existingFood.setName(
                newFood.getName()
        );

        existingFood.setPrice(
                newFood.getPrice()
        );

        existingFood.setCategory(
                newFood.getCategory()
        );

        return repo.save(existingFood);
    }
    //delete
    public String delete(int id){
        FoodItem food =
                repo.findByIdAndIsActiveTrue(id);
        if(food == null){

            throw new ResponseStatusException(
                    HttpStatus.NOT_FOUND,
                    "Food Not Found"
            );
        }
        String email=SecurityContextHolder.getContext().getAuthentication().getName();
        User loggedInUser=userRepo.findByEmail(email);
        if(food.getRestaurant().getOwner().getId() != loggedInUser.getId()) {
            throw new ResponseStatusException(HttpStatus.FORBIDDEN, "Access Denied");
        }
        food.setActive(false);
        repo.save(food);
        return "Food Deactivated";
    }


}
