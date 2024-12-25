package com.snacky.FoodOrderingApp_Back.Service;

import com.snacky.FoodOrderingApp_Back.Dto.AddToShoppingCartRequest;
import com.snacky.FoodOrderingApp_Back.Model.ShoppingCart.ShoppingCart;
import org.springframework.stereotype.Service;

@Service
public class ShoppingCartServiceImp implements ShoppingCartService {

    //let's call the unimplemented methods.
    @Override
    public ShoppingCart addToShoppingCart(AddToShoppingCartRequest request, String jwt) throws Exception {
        return null;
    }

    @Override
    public ShoppingCart updateShoppingCart(Long shoppingCartId, int quantity) throws Exception {
        return null;
    }

    @Override
    public ShoppingCart removeFromShoppingCart(Long shoppingCartItemId, String jwt) throws Exception {
        return null;
    }

    @Override
    public ShoppingCart calculateTotal(ShoppingCart shoppingCart) throws Exception {
        return null;
    }

    @Override
    public ShoppingCart findShoppingCartById(Long id) throws Exception {
        return null;
    }

    @Override
    public ShoppingCart findShoppingCartByUserId(Long userId) throws Exception {
        return null;
    }

    @Override
    public ShoppingCart cancelShoppingCart(Long userId) throws Exception {
        return null;
    }
}
