package com.anurag.fooddelivery.service;

import com.anurag.fooddelivery.dto.OrderRequest;
import com.anurag.fooddelivery.entity.CustomerOrder;
import com.anurag.fooddelivery.entity.OrderStatus;
import com.anurag.fooddelivery.repository.CustomerOrderRepository;
import com.anurag.fooddelivery.repository.FoodItemRepository;
import com.anurag.fooddelivery.repository.OrderItemRepository;
import com.anurag.fooddelivery.repository.RestaurantRepository;
import com.anurag.fooddelivery.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OrderService {

    @Autowired
    private CustomerOrderRepository orderRepo;

    @Autowired
    private OrderItemRepository orderItemRepo;

    @Autowired
    private FoodItemRepository foodRepo;

    @Autowired
    private RestaurantRepository restaurantRepo;

    @Autowired
    private UserRepository userRepo;

    public CustomerOrder placeOrder(OrderRequest request){
        return null;
    }

    public List<CustomerOrder> getMyOrders(){
        return null;
    }

    public CustomerOrder getOrderById(int id){
        return null;
    }

    public CustomerOrder updateStatus(int orderId,
                                      OrderStatus status){
        return null;
    }
}