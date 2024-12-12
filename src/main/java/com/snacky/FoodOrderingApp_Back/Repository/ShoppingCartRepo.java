package com.snacky.FoodOrderingApp_Back.Repository;

import com.snacky.FoodOrderingApp_Back.Model.ShoppingCart.ShoppingCart;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ShoppingCartRepo extends JpaRepository <ShoppingCart, Long> {


}
