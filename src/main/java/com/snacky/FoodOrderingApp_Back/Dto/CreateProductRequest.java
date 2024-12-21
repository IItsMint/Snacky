package com.snacky.FoodOrderingApp_Back.Dto;

import com.snacky.FoodOrderingApp_Back.Model.Product.Category;
import com.snacky.FoodOrderingApp_Back.Model.Product.Ingredients;
import com.snacky.FoodOrderingApp_Back.Model.Product.Product;
import lombok.Data;

import java.util.List;

@Data
public class CreateProductRequest {

    private String name;
    private String description;
    private Long price;
    private Integer quantity;
    private Long restaurantId;
    private Category category;

    private List<Ingredients> ingredients;
    private List<String> image;


    private boolean isVegan;
    private boolean isSeasonal;
    private boolean isAvailable;
}
