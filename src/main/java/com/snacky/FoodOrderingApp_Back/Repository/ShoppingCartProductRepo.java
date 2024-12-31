package com.snacky.FoodOrderingApp_Back.Repository;

import com.snacky.FoodOrderingApp_Back.Model.ShoppingCart.ShoppingCartProduct;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ShoppingCartProductRepo extends JpaRepository<ShoppingCartProduct, Long> {

    //we can add custom query methods if it is needed.

}
