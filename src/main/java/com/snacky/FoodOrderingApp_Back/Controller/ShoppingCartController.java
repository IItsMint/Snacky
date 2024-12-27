package com.snacky.FoodOrderingApp_Back.Controller;

import com.snacky.FoodOrderingApp_Back.Dto.AddToShoppingCartRequest;
import com.snacky.FoodOrderingApp_Back.Dto.UpdateShoppingCartProductRequest;
import com.snacky.FoodOrderingApp_Back.Model.ShoppingCart.ShoppingCart;
import com.snacky.FoodOrderingApp_Back.Model.ShoppingCart.ShoppingCartProduct;
import com.snacky.FoodOrderingApp_Back.Service.ShoppingCartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
public class ShoppingCartController {

    //let's first called to needed instances to wire them up.
    @Autowired
    private ShoppingCartService shoppingCartService;

    @PutMapping("/shoppingCart/add")
    public ResponseEntity<ShoppingCartProduct> addProductToShoppingCart(@RequestBody AddToShoppingCartRequest addToShoppingCartRequest,
                                                                        @RequestHeader("Authorization") String jwt) throws Exception{

        ShoppingCartProduct shoppingCartProduct = shoppingCartService.addToShoppingCart(addToShoppingCartRequest, jwt);

        return new ResponseEntity<>(shoppingCartProduct, HttpStatus.OK);
    }

    @PutMapping("/shoppingCartProduct/update")
    public ResponseEntity<ShoppingCartProduct> updateShoppingCartProduct(@RequestBody UpdateShoppingCartProductRequest updateShoppingCartProductRequest,
                                                                         @RequestHeader("Authorization") String jwt) throws Exception{

        ShoppingCartProduct shoppingCartProduct = shoppingCartService.updateShoppingCart(updateShoppingCartProductRequest.getShoppingCartProductId(), updateShoppingCartProductRequest.getQuantity());
        return new ResponseEntity<>(shoppingCartProduct, HttpStatus.OK);
    }

    @DeleteMapping("/shoppingCart/{id}/delete")
    public ResponseEntity<ShoppingCart> deleteShoppingCartProduct(@PathVariable Long id,
                                                                  @RequestHeader("Authorization") String jwt) throws Exception{

        ShoppingCart shoppingCart= shoppingCartService.removeFromShoppingCart(id, jwt);

        return new ResponseEntity<>(shoppingCart, HttpStatus.OK);
    }


}
