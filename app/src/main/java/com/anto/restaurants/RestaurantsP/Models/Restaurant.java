package com.anto.restaurants.RestaurantsP.Models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Restaurant {
    @SerializedName("restaurant")
    @Expose
    private Restaurant_ restaurant;

    public Restaurant_ getRestaurant() {
        return restaurant;
    }

    public void setRestaurant(Restaurant_ restaurant) {
        this.restaurant = restaurant;
    }

    @Override
    public String toString() {
        return "Restaurant{" +
                "restaurant=" + restaurant +
                '}';
    }
}
