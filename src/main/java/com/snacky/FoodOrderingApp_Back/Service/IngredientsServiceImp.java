package com.snacky.FoodOrderingApp_Back.Service;

import com.snacky.FoodOrderingApp_Back.Model.Product.IngredientCategory;
import com.snacky.FoodOrderingApp_Back.Model.Product.Ingredients;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class IngredientsServiceImp implements IngredientsService {

    //lets called unimplemented methods of the interface service.

    @Override
    public Ingredients createIngredients(Long restaurantId, String ingredientName, Long ingredientCategoryId) {
        return null;
    }

    @Override
    public IngredientCategory createIngredientCategory(String name, Long restaurantId) throws Exception {
        return null;
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
