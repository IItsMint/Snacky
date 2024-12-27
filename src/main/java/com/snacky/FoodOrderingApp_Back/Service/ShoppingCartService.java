package com.snacky.FoodOrderingApp_Back.Service;

import com.snacky.FoodOrderingApp_Back.Dto.AddToShoppingCartRequest;
import com.snacky.FoodOrderingApp_Back.Model.ShoppingCart.ShoppingCart;
import com.snacky.FoodOrderingApp_Back.Model.ShoppingCart.ShoppingCartProduct;

public interface ShoppingCartService {

    public ShoppingCartProduct addToShoppingCart(AddToShoppingCartRequest request, String jwt)throws Exception;
    public ShoppingCartProduct updateShoppingCart(Long shoppingCartId, int quantity)throws Exception;
    public ShoppingCartProduct removeFromShoppingCart(Long shoppingCartProductId, String jwt)throws Exception;
    public Long calculateTotal(ShoppingCart shoppingCart)throws Exception;
    public ShoppingCartProduct findShoppingCartById(Long id)throws Exception;
    public ShoppingCartProduct findShoppingCartByUserId(Long userId)throws Exception;
    public ShoppingCartProduct cancelShoppingCart(Long userId)throws Exception;
}
