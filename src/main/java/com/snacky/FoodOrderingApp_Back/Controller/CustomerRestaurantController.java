package com.snacky.FoodOrderingApp_Back.Controller;

import com.snacky.FoodOrderingApp_Back.Dto.RestaurantDto;
import com.snacky.FoodOrderingApp_Back.Model.Restaurant.Restaurant;
import com.snacky.FoodOrderingApp_Back.Model.User.User;
import com.snacky.FoodOrderingApp_Back.Service.RestaurantService;
import com.snacky.FoodOrderingApp_Back.Service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/restaurants")
public class CustomerRestaurantController {

    @Autowired
    private RestaurantService restaurantService;

    @Autowired
    private UserService userService;

    //in search method we are going to use list since, we can have multiple output for one keyword in searching.
    @GetMapping("/search")
    public ResponseEntity<List<Restaurant>> searchRestaurant(@RequestParam String keyword,
                                                             @RequestHeader("Authorization") String jwt) throws Exception{

        User user = userService.findByJwtToken(jwt);

        List<Restaurant> restaurant = restaurantService.searchRestaurant(keyword);

        return new ResponseEntity<>(restaurant, HttpStatus.OK);
    }

    @GetMapping("/all")
    public ResponseEntity<List<Restaurant>> getAllRestaurants (@RequestHeader("Authorization") String jwt) throws Exception{

        User user = userService.findByJwtToken(jwt);

        List<Restaurant> restaurants = restaurantService.getAllRestaurants();

        return new ResponseEntity<>(restaurants, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Restaurant> findRestaurantById(@PathVariable Long id,
                                                         @RequestHeader("Authorization") String jwt) throws Exception{

        User user = userService.findByJwtToken(jwt);

        Restaurant restaurant = restaurantService.findRestaurantById(id);
        return new ResponseEntity<>(restaurant, HttpStatus.OK);
    }

    //we don't need the full entity since we only need related fields like name, description etc. So we used DTO.
    @PutMapping("/{id}/favourites")
    public ResponseEntity<RestaurantDto> addFavourites(@PathVariable Long id,
                                                    @RequestHeader("Authorization") String jwt) throws Exception{

        User user = userService.findByJwtToken(jwt);

        RestaurantDto restaurantDto = restaurantService.addToFavorites(id, user);

        return new ResponseEntity<>(restaurantDto, HttpStatus.CREATED);
    }


}
