package com.snacky.FoodOrderingApp_Back.Dto;

import lombok.Data;

@Data
public class UpdateShoppingCartProductRequest {

private Long shoppingCartProductId;
private int quantity;
}
