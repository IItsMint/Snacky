package com.snacky.FoodOrderingApp_Back.Dto;

import lombok.Data;

@Data
public class IngredientRequest {

    private String name;
    private Long categoryId;
    private Long restaurantId;

}
