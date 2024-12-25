package com.snacky.FoodOrderingApp_Back.Controller;

import com.snacky.FoodOrderingApp_Back.Dto.IngredientCategoryRequest;
import com.snacky.FoodOrderingApp_Back.Dto.IngredientRequest;
import com.snacky.FoodOrderingApp_Back.Model.Product.IngredientCategory;
import com.snacky.FoodOrderingApp_Back.Model.Product.Ingredients;
import com.snacky.FoodOrderingApp_Back.Service.IngredientsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/admin/ingredients")
public class IngredientController {

    //lets connect the service.
    @Autowired
    private IngredientsService ingredientsService;


    @PostMapping()
    public  ResponseEntity<Ingredients> createIngredient(@RequestBody IngredientRequest ingredientRequest) throws Exception {

        Ingredients ingredient = ingredientsService.createIngredients(ingredientRequest.getRestaurantId(), ingredientRequest.getName(), ingredientRequest.getCategoryId());
        return new ResponseEntity<>(ingredient, HttpStatus.CREATED);
    }

    @PostMapping("/category")
    public ResponseEntity<IngredientCategory> createIngredientCategory(@RequestBody IngredientCategoryRequest ingredientCategoryRequest) throws Exception {

        IngredientCategory ingredientCategory = ingredientsService.createIngredientCategory(ingredientCategoryRequest.getName(), ingredientCategoryRequest.getRestaurantId());

        return new ResponseEntity<>(ingredientCategory, HttpStatus.CREATED);
    }

    @PutMapping("/restaurant/{id}/stock")
    public ResponseEntity<Ingredients> updateIngredientStock(@PathVariable Long id) throws Exception {

        Ingredients ingredients = ingredientsService.updateStock(id);
        return new ResponseEntity<>(ingredients, HttpStatus.OK);
    }

    @GetMapping("/restaurant/{id}")
    public ResponseEntity<List<Ingredients>> getRestaurantIngredients(@PathVariable Long id) throws Exception {

        List<Ingredients> ingredients = ingredientsService.getAllIngredientsRestaurant(id);
        return new ResponseEntity<>(ingredients, HttpStatus.OK);
    }

    @GetMapping("/restaurant/{id}/category")
    public ResponseEntity<List<IngredientCategory>> getRestaurantIngredientCategory(@PathVariable Long id) throws Exception {

        List<IngredientCategory> ingredients = ingredientsService.getIngredientCategoryByRestaurantId(id);
        return new ResponseEntity<>(ingredients, HttpStatus.OK);
    }

    @PutMapping("/restaurant/category/ingredient/update/{id}")
    public ResponseEntity<IngredientCategory> updateIngredientCategory(@PathVariable Long id,
                                                                       @RequestBody String newCategoryName) {
        try {
            // Call service to update the category with the new name.
            IngredientCategory updated = ingredientsService.updateIngredientCategory(id, newCategoryName);

            return new ResponseEntity<>(updated, HttpStatus.OK);
        }
        catch (IllegalArgumentException e) {
            // Handle the case where the ingredient category is not found or invalid.
            return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
        }
        catch (Exception e) {
            // Handle general errors, if necessary.
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }


    @DeleteMapping("/restaurant/ingredient/category/delete/{id}")
    public ResponseEntity<String> deleteIngredientCategory(@PathVariable Long id) {

        try {
            ingredientsService.deleteIngredientCategory(id);
            return new ResponseEntity<>("Ingredient category deleted successfully.", HttpStatus.OK);
        }
        catch (Exception e) {
            return new ResponseEntity<>("Error deleting ingredient category: " + e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

}
