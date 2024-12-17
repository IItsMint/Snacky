package com.snacky.FoodOrderingApp_Back.Service;

import com.snacky.FoodOrderingApp_Back.Dto.CreateRestaurantRequest;
import com.snacky.FoodOrderingApp_Back.Dto.RestaurantDto;
import com.snacky.FoodOrderingApp_Back.Model.Address.Address;
import com.snacky.FoodOrderingApp_Back.Model.Restaurant.Restaurant;
import com.snacky.FoodOrderingApp_Back.Model.User.User;
import com.snacky.FoodOrderingApp_Back.Repository.AddressRepo;
import com.snacky.FoodOrderingApp_Back.Repository.RestaurantRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class RestaurantServiceImp implements RestaurantService {

    //first we need to call some files that we are going to do some business with them.

    //let's call restaurant repo
    @Autowired
    private RestaurantRepo restaurantRepo;

    //let's call address repo.
    @Autowired
    private AddressRepo addressRepo;

    //let's call user service.
    @Autowired
    private UserService userService;


    //now we can define the business logic of each method.
    //let's call unimplemented methods.

    @Override
    public Restaurant createRestaurant(CreateRestaurantRequest req, User user) {

        //first let's take address object and save it to repo,
        Address address = addressRepo.save(req.getAddress());

        //second let's create new instances of a restaurant entity,
        Restaurant restaurant = new Restaurant();

        //and now, let's define property.
        restaurant.setAddress(address);
        restaurant.setName(req.getName());
        restaurant.setContact(req.getContactInformation());
        restaurant.setDescription(req.getDescription());
        restaurant.setCuisineType(req.getCuisine());
        restaurant.setImages(req.getImages());
        restaurant.setWorkingHours(req.getWorkingHours());
        restaurant.setCreatedAt(LocalDateTime.now());
        restaurant.setOwner(user);

        //save this restaurant to the database.
        return restaurantRepo.save(restaurant);
    }

    @Override
    public Restaurant updateRestaurant(Long restaurantId, CreateRestaurantRequest updatedRestaurant) throws Exception {

        Restaurant restaurant = findRestaurantById(restaurantId);
    }

    @Override
    public Restaurant deleteRestaurant(Long restaurantId) throws Exception {
        return null;
    }

    @Override
    public List<Restaurant> searchRestaurant() {
        return List.of();
    }

    @Override
    public Restaurant findRestaurantById(Long restaurantId) throws Exception {
        return null;
    }

    @Override
    public Restaurant findRestaurantByUserId(Long userId) throws Exception {
        return null;
    }

    @Override
    public RestaurantDto addToFavorites(Long restaurantId, User user) throws Exception {
        return null;
    }

    @Override
    public Restaurant updateRestaurantStatus(Long id) throws Exception {
        return null;
    }

    @Override
    public List<Restaurant> getAllRestaurants() {
        return List.of();
    }
}
