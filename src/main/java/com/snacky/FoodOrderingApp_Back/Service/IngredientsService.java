package com.snacky.FoodOrderingApp_Back.Service;

import com.snacky.FoodOrderingApp_Back.Model.Product.IngredientCategory;
import com.snacky.FoodOrderingApp_Back.Model.Product.Ingredients;

import java.util.List;

public interface IngredientsService {

    //let's write all the methods for ingredients service.

    public Ingredients createIngredients(Long restaurantId, String ingredientName, Long ingredientCategoryId);
    public IngredientCategory createIngredientCategory(String name, Long restaurantId) throws Exception;
    public IngredientCategory updateIngredientCategory(IngredientCategory ingredientCategory) throws Exception;
    public void deleteIngredientCategory(IngredientCategory ingredientCategory) throws Exception;

    public IngredientCategory findIngredientCategoryById(Long id) throws Exception;
    public IngredientCategory getIngredientCategoryByRestaurantId(Long id) throws Exception;
    public List<Ingredients> getAllIngredientsRestaurant(Long restaurantId) throws Exception;

}
