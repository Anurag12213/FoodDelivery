package com.anurag.fooddelivery.dto;

import java.util.List;

public class OrderRequest {

    private int restaurantId;

    private List<OrderRequestItem> items;

    public int getRestaurantId() {
        return restaurantId;
    }

    public void setRestaurantId(int restaurantId) {
        this.restaurantId = restaurantId;
    }

    public List<OrderRequestItem> getItems() {
        return items;
    }

    public void setItems(List<OrderRequestItem> items) {
        this.items = items;
    }
}