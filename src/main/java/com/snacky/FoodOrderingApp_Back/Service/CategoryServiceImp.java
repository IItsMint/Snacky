package com.snacky.FoodOrderingApp_Back.Service;

import com.snacky.FoodOrderingApp_Back.Model.Product.Category;
import com.snacky.FoodOrderingApp_Back.Model.Restaurant.Restaurant;
import com.snacky.FoodOrderingApp_Back.Repository.CategoryRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CategoryServiceImp implements CategoryService{

    //we need to wire restaurant service here,
    @Autowired
    private RestaurantService restaurantService;

    //we need to also wire the repo here.
    @Autowired
    private CategoryRepo categoryRepo;

    //now lets call the unimplemented methods.
    @Override
    public Category createCategory(String categoryName, Long userId) throws Exception {

        //first we need to find the restaurant.
        Restaurant restaurant = restaurantService.findRestaurantById(userId);

        //after finding restaurant, lets create category,
        Category category = new Category();

        category.setName(categoryName);
        category.setRestaurant(restaurant);

        //now let's save this category.
        return categoryRepo.save(category);
    }

    @Override
    public List<Category> findCategoryByRestaurantId(Long id) throws Exception {
        return List.of();
    }

    @Override
    public Category findCategoryById(Long id) throws Exception {
        return null;
    }




}
