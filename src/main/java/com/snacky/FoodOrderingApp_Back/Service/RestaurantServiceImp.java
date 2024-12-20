package com.snacky.FoodOrderingApp_Back.Service;

import com.snacky.FoodOrderingApp_Back.Dto.CreateRestaurantRequest;
import com.snacky.FoodOrderingApp_Back.Dto.RestaurantDto;
import com.snacky.FoodOrderingApp_Back.Dto.UpdateRestaurantRequest;
import com.snacky.FoodOrderingApp_Back.Model.Address.Address;
import com.snacky.FoodOrderingApp_Back.Model.Restaurant.Restaurant;
import com.snacky.FoodOrderingApp_Back.Model.User.User;
import com.snacky.FoodOrderingApp_Back.Repository.AddressRepo;
import com.snacky.FoodOrderingApp_Back.Repository.RestaurantRepo;
import com.snacky.FoodOrderingApp_Back.Repository.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

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
    private UserRepo userRepo;


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
    public Restaurant deleteRestaurant(Long restaurantId) throws Exception {
        // Find the restaurant by ID
        Restaurant restaurant = findRestaurantById(restaurantId);

        // Delete the restaurant
        restaurantRepo.delete(restaurant);
        // No need to return anything, just void
        return restaurant;
    }

    @Override
    public List<Restaurant> searchRestaurant(String keyword) {
        if (keyword == null || keyword.trim().isEmpty()) {
            throw new IllegalArgumentException("Search keyword cannot be empty");
        }
        return restaurantRepo.findBySearchQuery(keyword);
    }

    @Override
    public Restaurant findRestaurantById(Long id) throws Exception {

        Optional<Restaurant> optionalRestaurant = restaurantRepo.findById(id);

        if(optionalRestaurant.isEmpty()){
            throw new Exception("Restaurant not found with id" + id);
        }

        else {
            return optionalRestaurant.get();
        }
    }

    @Override
    public Restaurant findRestaurantByUserId(Long userId) throws Exception {

        Restaurant restaurant = restaurantRepo.findByOwnerId(userId);

        if(restaurant == null){
            throw new Exception("Restaurant not found for the owner with userId: " + userId);
        }

        return restaurant;
    }

    @Override
    public RestaurantDto addToFavorites(Long restaurantId, User user) throws Exception {

        // Fetch the restaurant by its ID
        Restaurant restaurant = findRestaurantById(restaurantId);

        // Create a DTO for the restaurant
        RestaurantDto restaurantDto = new RestaurantDto();
        restaurantDto.setId(restaurant.getId());
        restaurantDto.setTitle(restaurant.getName());
        restaurantDto.setDescription(restaurant.getDescription());
        restaurantDto.setImages(restaurant.getImages());

        // Check if the user already has this restaurant in their favorites
        boolean isFavorite = user.getFavorites().stream()
                .anyMatch(fav -> fav.getId().equals(restaurantDto.getId()));

        if (isFavorite) {
            // If it's already a favorite, remove it
            user.getFavorites().removeIf(fav -> fav.getId().equals(restaurantDto.getId()));
        }

        else {
            // If it's not a favorite, add it
            user.getFavorites().add(restaurantDto);
        }

        // Save the updated user object
        userRepo.save(user);

        return restaurantDto;
    }


    @Override
    public Restaurant updateRestaurantStatus(Long id) throws Exception {

        Restaurant restaurant = findRestaurantById(id);

        //If the restaurant already open it will close, if it is already close it will open.
        restaurant.setActive(!restaurant.isActive());

        return restaurantRepo.save(restaurant);
    }

    @Override
    public List<Restaurant> getAllRestaurants() {
        return restaurantRepo.findAll();
    }
}
