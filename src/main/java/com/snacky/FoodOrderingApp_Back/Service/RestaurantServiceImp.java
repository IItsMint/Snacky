package com.snacky.FoodOrderingApp_Back.Service;

import com.snacky.FoodOrderingApp_Back.Dto.CreateRestaurantRequest;
import com.snacky.FoodOrderingApp_Back.Dto.RestaurantDto;
import com.snacky.FoodOrderingApp_Back.Dto.UpdateRestaurantRequest;
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
    public Restaurant updateRestaurant(Long restaurantId, UpdateRestaurantRequest updatedRestaurant) throws Exception {

        // Fetch the existing restaurant
        Restaurant restaurant = findRestaurantById(restaurantId);

        // Update only the provided fields from the updatedRestaurant DTO
        if (updatedRestaurant.getName() != null) {
            restaurant.setName(updatedRestaurant.getName());
        }

        if (updatedRestaurant.getCuisine() != null) {
            restaurant.setCuisineType(updatedRestaurant.getCuisine());
        }

        if (updatedRestaurant.getDescription() != null) {
            restaurant.setDescription(updatedRestaurant.getDescription());
        }

        if (updatedRestaurant.getAddress() != null) {
            Address updatedAddress = updatedRestaurant.getAddress();
            Address existingAddress = restaurant.getAddress();

            // Update address fields individually
            if (updatedAddress.getStreet() != null) {
                existingAddress.setStreet(updatedAddress.getStreet());
            }
            if (updatedAddress.getCity() != null) {
                existingAddress.setCity(updatedAddress.getCity());
            }
            if (updatedAddress.getPostalCode() != null) {
                existingAddress.setPostalCode(updatedAddress.getPostalCode());
            }
            if (updatedAddress.getCountry() != null) {
                existingAddress.setCountry(updatedAddress.getCountry());
            }

            restaurant.setAddress(existingAddress); // Save the updated address back to the restaurant
        }

        if (updatedRestaurant.getContactInformation() != null) {
            restaurant.setContact(updatedRestaurant.getContactInformation());
        }

        if (updatedRestaurant.getImages() != null) {
            restaurant.setImages(updatedRestaurant.getImages());
        }

        if (updatedRestaurant.getWorkingHours() != null) {
            restaurant.setWorkingHours(updatedRestaurant.getWorkingHours());
        }

        // Save the updated restaurant back to the database
        return restaurantRepo.save(restaurant);
    }


    @Override
    public void deleteRestaurant(Long restaurantId) throws Exception {
        // Find the restaurant by ID
        Restaurant restaurant = findRestaurantById(restaurantId);

        // Delete the restaurant
        restaurantRepo.delete(restaurant);
        // No need to return anything, just void
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
