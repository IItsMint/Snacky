package com.snacky.FoodOrderingApp_Back.Service;

import com.snacky.FoodOrderingApp_Back.Model.Product.IngredientCategory;
import com.snacky.FoodOrderingApp_Back.Model.Product.Ingredients;
import com.snacky.FoodOrderingApp_Back.Model.Restaurant.Restaurant;
import com.snacky.FoodOrderingApp_Back.Repository.IngredientCategoryRepo;
import com.snacky.FoodOrderingApp_Back.Repository.IngredientRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class IngredientsServiceImp implements IngredientsService {

    //let's first call classes we will use in implementation.

    @Autowired
    private IngredientRepo ingredientRepo;

    @Autowired
    private IngredientCategoryRepo ingredientCategoryRepo;

    @Autowired
    private RestaurantService restaurantService;

    //lets called unimplemented methods of the interface service.

    @Override
    public Ingredients createIngredients(Long restaurantId, String ingredientName, Long ingredientCategoryId) throws Exception {

        return null;
    }

    @Override
    public IngredientCategory createIngredientCategory(String name, Long restaurantId) throws Exception {

        //first let's find the restaurant,
        Restaurant restaurant = restaurantService.findRestaurantById(restaurantId);

        //now we can create ingredient category.
        IngredientCategory ingredientCategory = new IngredientCategory();

        ingredientCategory.setRestaurant(restaurant);
        ingredientCategory.setName(name);

        return ingredientCategoryRepo.save(ingredientCategory);
    }

    @Override
    public IngredientCategory updateIngredientCategory(IngredientCategory ingredientCategory) throws Exception {
        return null;
    }

    @Override
    public void deleteIngredientCategory(IngredientCategory ingredientCategory) throws Exception {

    }

    @Override
    public IngredientCategory findIngredientCategoryById(Long id) throws Exception {
        return null;
    }

    @Override
    public IngredientCategory getIngredientCategoryByRestaurantId(Long id) throws Exception {
        return null;
    }

    @Override
    public List<Ingredients> getAllIngredientsRestaurant(Long restaurantId) throws Exception {
        return List.of();
    }

}
