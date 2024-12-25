package com.snacky.FoodOrderingApp_Back.Repository;

import com.snacky.FoodOrderingApp_Back.Model.Product.IngredientCategory;
import com.snacky.FoodOrderingApp_Back.Model.Restaurant.Restaurant;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface IngredientCategoryRepo extends JpaRepository<IngredientCategory, Long> {

    //with this method we are checking with duplicates in service layer.
    boolean existsByNameAndRestaurant(String name, Restaurant restaurant);
    List<IngredientCategory> findByRestaurantId(Long id);
    void deleteIngredientCategoryById(Long id);
    IngredientCategory save(IngredientCategory ingredientCategory);
}
