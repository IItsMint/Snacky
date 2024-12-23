package com.snacky.FoodOrderingApp_Back.Controller;

import com.snacky.FoodOrderingApp_Back.Dto.CreateProductRequest;
import com.snacky.FoodOrderingApp_Back.Model.Product.Product;
import com.snacky.FoodOrderingApp_Back.Model.Restaurant.Restaurant;
import com.snacky.FoodOrderingApp_Back.Model.User.User;
import com.snacky.FoodOrderingApp_Back.Service.ProductService;
import com.snacky.FoodOrderingApp_Back.Service.RestaurantService;
import com.snacky.FoodOrderingApp_Back.Service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/admin/products")
public class AdminProductController {

    //first lets call service layers that we are gonna route.

    @Autowired
    private ProductService productService;

    @Autowired
    private UserService userService;

    @Autowired
    private RestaurantService restaurantService;

    @PostMapping
    public ResponseEntity<Product> createProduct(@RequestBody CreateProductRequest request,
                                                 @RequestHeader("Authorization") String jwt) throws Exception{

        //first lets get user,
        User user = userService.findByJwtToken(jwt);

        //now lets find restaurant,
        Restaurant restaurant = restaurantService.findRestaurantById(request.getRestaurantId());

        //now we can create the product,
        Product product = productService.addProduct(request, request.getCategory(), restaurant);

        return new ResponseEntity<>(product, HttpStatus.CREATED);
    }

    @PutMapping("/{id}/status")
    public ResponseEntity<Product> updateProductStatus (@PathVariable Long id,
                                                        @RequestHeader("Authorization") String jwt) throws Exception{
        //first lets get user,
        User user = userService.findByJwtToken(jwt);

        // Find the product
        Product product = productService.findProductById(id);

        // Optional: Check if the product belongs to a restaurant owned by the user
        Restaurant restaurant = product.getRestaurant();
        if (restaurant == null || !restaurant.getOwner().equals(user)) {
            throw new Exception("User not authorized to change the status of this product.");
        }

        //now change the status of the product,
        Product updatedProduct = productService.updateStatus(id);

        return new ResponseEntity<>(updatedProduct, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteProduct(@RequestHeader("Authorization") String jwt,
                                                @PathVariable long id) throws Exception {
        // Validate the user from the JWT token
        User user = userService.findByJwtToken(jwt);

        // Find the product
        Product product = productService.findProductById(id);

        // Optional: Check if the product belongs to a restaurant owned by the user
        Restaurant restaurant = product.getRestaurant();
        if (restaurant == null || !restaurant.getOwner().equals(user)) {
            throw new Exception("User not authorized to update this product.");
        }

        // Delete the product
        productService.deleteProduct(id);

        // Return success message
        return new ResponseEntity<>("Product deleted successfully.", HttpStatus.OK);
    }





}
