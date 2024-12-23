package com.snacky.FoodOrderingApp_Back.Controller;

import com.snacky.FoodOrderingApp_Back.Model.Product.Product;
import com.snacky.FoodOrderingApp_Back.Service.ProductService;
import com.snacky.FoodOrderingApp_Back.Service.RestaurantService;
import com.snacky.FoodOrderingApp_Back.Service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/products")
public class CustomerProductController {

    //First of all lets call the service layers that we are gonna use.

    @Autowired
    private ProductService productService;

    @Autowired
    private UserService userService;

    @Autowired
    private RestaurantService restaurantService;


    //Let's implement search product first.
    @GetMapping("/search")
    public ResponseEntity<List<Product>> searchProduct(@RequestParam String keyword)throws Exception{

        List<Product> products = productService.searchProduct(keyword);

        return new ResponseEntity<>(products, HttpStatus.OK);
    }

    //now lets implement searching on a restaurant.
    @GetMapping("/restaurant/{restaurantId}/")
    public ResponseEntity<List<Product>> searchRestaurantProduct(@RequestParam boolean vegan,
                                                                 @RequestParam boolean seasonal,
                                                                 @RequestParam (required = false) String productCategory,
                                                                 @PathVariable Long restaurantId)throws Exception{

        List<Product> products = productService.getAllProducts(restaurantId, vegan, seasonal, productCategory);

        return new ResponseEntity<>(products, HttpStatus.OK);
    }

}
