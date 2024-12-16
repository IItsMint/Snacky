package com.snacky.FoodOrderingApp_Back.Service;

import com.snacky.FoodOrderingApp_Back.Dto.CreateRestaurantRequest;
import com.snacky.FoodOrderingApp_Back.Dto.RestaurantDto;
import com.snacky.FoodOrderingApp_Back.Model.Restaurant.Restaurant;
import com.snacky.FoodOrderingApp_Back.Model.User.User;


import java.util.List;

public interface RestaurantService {

    public Restaurant createRestaurant(CreateRestaurantRequest req, User user);
    public Restaurant updateRestaurant(Long restaurantId, CreateRestaurantRequest updatedRestaurant) throws Exception;
    public Restaurant deleteRestaurant(Long restaurantId) throws Exception;
    public List<Restaurant> searchRestaurant();
    public Restaurant findRestaurantById(Long restaurantId) throws Exception;
    public Restaurant findRestaurantByUserId(Long userId) throws Exception;
    public RestaurantDto addToFavorites(Long restaurantId, User user) throws Exception;
    public Restaurant updateRestaurantStatus(Long id) throws Exception;

    //this will be accessible for only admin.
    public List<Restaurant> getAllRestaurants();
}
