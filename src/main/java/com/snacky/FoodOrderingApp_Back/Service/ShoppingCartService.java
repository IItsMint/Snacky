package com.snacky.FoodOrderingApp_Back.Service;

import com.snacky.FoodOrderingApp_Back.Dto.AddToShoppingCartRequest;
import com.snacky.FoodOrderingApp_Back.Model.ShoppingCart.ShoppingCart;

public interface ShoppingCartService {

    public ShoppingCart addToShoppingCart(AddToShoppingCartRequest request, String jwt)throws Exception;
    public ShoppingCart updateShoppingCart(Long shoppingCartId, int quantity)throws Exception;
    public ShoppingCart removeFromShoppingCart(Long shoppingCartProductId, String jwt)throws Exception;
    public ShoppingCart calculateTotal(ShoppingCart shoppingCart)throws Exception;
    public ShoppingCart findShoppingCartById(Long id)throws Exception;
    public ShoppingCart findShoppingCartByUserId(Long userId)throws Exception;
    public ShoppingCart cancelShoppingCart(Long userId)throws Exception;
}
