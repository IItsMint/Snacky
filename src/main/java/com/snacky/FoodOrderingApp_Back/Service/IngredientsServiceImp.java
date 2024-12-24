package com.snacky.FoodOrderingApp_Back.Service;

import com.snacky.FoodOrderingApp_Back.Model.Product.IngredientCategory;
import com.snacky.FoodOrderingApp_Back.Model.Product.Ingredients;
import com.snacky.FoodOrderingApp_Back.Model.Restaurant.Restaurant;
import com.snacky.FoodOrderingApp_Back.Repository.IngredientCategoryRepo;
import com.snacky.FoodOrderingApp_Back.Repository.IngredientRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

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

        //let's check if it is empty or not.
        if (name == null || name.trim().isEmpty()) {
            throw new IllegalArgumentException("Category name cannot be null or empty.");
        }

        //let's check if its exist or not.
        if (ingredientCategoryRepo.existsByNameAndRestaurant(name, restaurant)) {
            throw new Exception("Ingredient category with this name already exists for the restaurant.");
        }

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
        return ingredientCategoryRepo.findById(id)
                .orElseThrow(() -> new Exception("Ingredient category with this id does not exist."));
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
