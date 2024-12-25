package com.snacky.FoodOrderingApp_Back.Repository;

import com.snacky.FoodOrderingApp_Back.Model.Product.Ingredients;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface IngredientRepo extends JpaRepository <Ingredients, Long> {

    List<Ingredients> findByRestaurantId(Long restaurantId);

}
