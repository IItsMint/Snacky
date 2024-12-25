package com.snacky.FoodOrderingApp_Back.Controller;

import com.snacky.FoodOrderingApp_Back.Dto.IngredientCategoryRequest;
import com.snacky.FoodOrderingApp_Back.Model.Product.IngredientCategory;
import com.snacky.FoodOrderingApp_Back.Service.IngredientsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/admin/ingredients")
public class IngredientController {

    //lets connect the service.
    @Autowired
    private IngredientsService ingredientsService;

    public ResponseEntity<IngredientCategory> createIngredientCategory(@RequestBody IngredientCategoryRequest ingredientCategoryRequest) throws Exception {

        IngredientCategory ingredientCategory = ingredientsService.createIngredientCategory(ingredientCategoryRequest.getName(), ingredientCategoryRequest.getRestaurantId());

        return new ResponseEntity<>(ingredientCategory, HttpStatus.CREATED);
    }
}
