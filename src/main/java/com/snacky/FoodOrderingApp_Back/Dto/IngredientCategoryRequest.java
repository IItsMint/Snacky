package com.snacky.FoodOrderingApp_Back.Dto;

import lombok.Data;

@Data
public class IngredientCategoryRequest {

    private String name;
    private Long restaurantId;
}
