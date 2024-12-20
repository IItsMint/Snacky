package com.snacky.FoodOrderingApp_Back.Controller;

import com.snacky.FoodOrderingApp_Back.Dto.CreateRestaurantRequest;
import com.snacky.FoodOrderingApp_Back.Dto.UpdateRestaurantRequest;
import com.snacky.FoodOrderingApp_Back.Model.Restaurant.Restaurant;
import com.snacky.FoodOrderingApp_Back.Model.User.User;
import com.snacky.FoodOrderingApp_Back.Service.RestaurantService;
import com.snacky.FoodOrderingApp_Back.Service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/admin/restaurants") //starting end point.
public class AdminRestaurantController {

    @Autowired
    private RestaurantService restaurantService;

    @Autowired
    private UserService userService;

    //we used post for creating.
    @PostMapping
    public ResponseEntity<Restaurant> createRestaurant(@RequestBody CreateRestaurantRequest req,//we are requesting body here.
                                                       @RequestHeader("Authorization") String jwt) throws Exception {//JWT token to fetch the user details.
        //first we need to get user from jwt.
        User user = userService.findByJwtToken(jwt);

        //now we can create restaurant.
        Restaurant restaurant = restaurantService.createRestaurant(req, user);

        return new ResponseEntity<>(restaurant, HttpStatus.CREATED);
    }

    //we used put for updating.
    @PutMapping("{id}")
    public ResponseEntity<Restaurant> updateRestaurant(@RequestBody UpdateRestaurantRequest req, //we are requesting body here.
                                                       @RequestHeader("Authorization") String jwt, //JWT token to fetch the user details.
                                                       @PathVariable Long id) throws Exception { //ensures the correct restaurant is updated.

        //again we first need to user details from the provided token.
        User user = userService.findByJwtToken(jwt);

        Restaurant restaurant = restaurantService.updateRestaurant(id,req);

        return new ResponseEntity<>(restaurant, HttpStatus.OK);
    }


}