package com.snacky.FoodOrderingApp_Back.Service;

import com.snacky.FoodOrderingApp_Back.Model.Product.IngredientCategory;
import com.snacky.FoodOrderingApp_Back.Model.Product.Ingredients;

import java.util.List;

public interface IngredientsService {

    //let's write all the methods for ingredients service.

    public Ingredients createIngredients(Long restaurantId, String ingredientName, Long ingredientCategoryId)throws Exception;
    public IngredientCategory createIngredientCategory(String name, Long restaurantId) throws Exception;
    public IngredientCategory updateIngredientCategory(Long id, String newName) throws Exception;
    public void deleteIngredientCategory(Long id) throws Exception;
    public Ingredients updateStock(Long id) throws Exception;

    public IngredientCategory findIngredientCategoryById(Long id) throws Exception;
    public List<IngredientCategory> getIngredientCategoryByRestaurantId(Long id) throws Exception;
    public List<Ingredients> getAllIngredientsRestaurant(Long restaurantId) throws Exception;

}
