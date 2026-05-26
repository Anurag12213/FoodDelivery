package com.anurag.fooddelivery.service;

import com.anurag.fooddelivery.entity.FoodItem;
import com.anurag.fooddelivery.entity.User;
import com.anurag.fooddelivery.repository.RestaurantRepository;
import com.anurag.fooddelivery.entity.Restaurant;
import com.anurag.fooddelivery.dto.RestaurantDTO;
import com.anurag.fooddelivery.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@Service
public class RestaurantService {

    @Autowired
    RestaurantRepository repo;
    @Autowired
    UserRepository userRepo;
    //createRestorent
    public Restaurant createRestaurant(Restaurant restaurant){

        String email =
                SecurityContextHolder
                        .getContext()
                        .getAuthentication()
                        .getName();
//        System.out.println(email);

        User owner =
                userRepo.findByEmail(email);
//        System.out.println(owner);

        restaurant.setOwner(owner);

        restaurant.setActive(true);

        return repo.save(restaurant);
    }

    //getAll
    public List<Restaurant> getAllRestaurant(){
        return repo.findByIsActiveTrue();
    }

    //get By ID
    public Restaurant getById(int id){
        return repo.findByIdAndIsActiveTrue(id);
    }

    //update by id
    public Restaurant update(int id,Restaurant newRestaurant){

        Restaurant existingRestaurant=repo.findByIdAndIsActiveTrue(id);
        if(existingRestaurant == null){

            throw new ResponseStatusException(
                    HttpStatus.NOT_FOUND,
                    "Restaurant Not Found"
            );
        }
        String email = SecurityContextHolder.getContext().getAuthentication().getName();
        User loggedInUser=userRepo.findByEmail(email);
        if(existingRestaurant.getOwner().getId() != loggedInUser.getId()){
            throw new ResponseStatusException(
                    HttpStatus.FORBIDDEN,
                    "Access Denied"
            );
        }
        existingRestaurant.setName(newRestaurant.getName());
        existingRestaurant.setLocation(newRestaurant.getLocation());
        existingRestaurant.setDescription(newRestaurant.getDescription());
        return repo.save(existingRestaurant);
    }

    //delete
    public String delete(int id){

        Restaurant restaurant =
                repo.findByIdAndIsActiveTrue(id);
        if(restaurant == null){
            throw new ResponseStatusException(
                    HttpStatus.NOT_FOUND,
                    "Restaurant Not Found"
            );
        }

        String email =
                SecurityContextHolder
                        .getContext()
                        .getAuthentication()
                        .getName();

        User loggedInUser =
                userRepo.findByEmail(email);

        if(restaurant.getOwner().getId()
                != loggedInUser.getId()){

            throw new ResponseStatusException(
                    HttpStatus.FORBIDDEN,
                    "Access Denied"
            );
        }

        restaurant.setActive(false);

        repo.save(restaurant);

        return "Restaurant Deactivated";
    }

    //GetAllRestaurantFoods
    public List<FoodItem> getRestaurantFoods(int id){
        Restaurant restaurant =
                repo.findByIdAndIsActiveTrue(id);
        return restaurant.getFoods();
    }

    //getRestaurantDTO
    public RestaurantDTO getRestaurantDTO(int id){
        Restaurant restaurant =
                repo.findByIdAndIsActiveTrue(id);
        RestaurantDTO dto=new RestaurantDTO();
        dto.setId(restaurant.getId());
        dto.setName(restaurant.getName());
        dto.setLocation(restaurant.getLocation());
        return dto;
    }

}
