package com.snacky.FoodOrderingApp_Back.Dto;

import com.snacky.FoodOrderingApp_Back.Model.Product.Ingredients;
import lombok.Data;

import java.util.List;

@Data
public class AddToShoppingCartRequest {

    private Long productId;
    private int quantity;
    private List<String> ingredients;
}
