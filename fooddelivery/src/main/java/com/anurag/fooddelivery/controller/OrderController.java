package com.anurag.fooddelivery.controller;

import com.anurag.fooddelivery.dto.OrderRequest;
import com.anurag.fooddelivery.entity.CustomerOrder;
import com.anurag.fooddelivery.entity.OrderStatus;
import com.anurag.fooddelivery.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/orders")
public class OrderController {

    @Autowired
    private OrderService service;

    @PostMapping
    public CustomerOrder placeOrder(
            @RequestBody OrderRequest request){

        return service.placeOrder(request);
    }

    @GetMapping("/my")
    public List<CustomerOrder> getMyOrders(){

        return service.getMyOrders();
    }

    @GetMapping("/{id}")
    public CustomerOrder getOrderById(
            @PathVariable int id){

        return service.getOrderById(id);
    }

    @PutMapping("/{id}/status")
    public CustomerOrder updateStatus(
            @PathVariable int id,
            @RequestParam OrderStatus status){

        return service.updateStatus(id,status);
    }
}