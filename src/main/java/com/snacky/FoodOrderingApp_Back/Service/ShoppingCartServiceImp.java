package com.snacky.FoodOrderingApp_Back.Service;

import com.snacky.FoodOrderingApp_Back.Dto.AddToShoppingCartRequest;
import com.snacky.FoodOrderingApp_Back.Model.Product.Product;
import com.snacky.FoodOrderingApp_Back.Model.ShoppingCart.ShoppingCart;
import com.snacky.FoodOrderingApp_Back.Model.ShoppingCart.ShoppingCartProduct;
import com.snacky.FoodOrderingApp_Back.Model.User.User;
import com.snacky.FoodOrderingApp_Back.Repository.ProductRepo;
import com.snacky.FoodOrderingApp_Back.Repository.ShoppingCartProductRepo;
import com.snacky.FoodOrderingApp_Back.Repository.ShoppingCartRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ShoppingCartServiceImp implements ShoppingCartService {

    //we need to wire some instances,
    @Autowired
    private UserService userService;

    @Autowired
    private ShoppingCartRepo shoppingCartRepo;

    @Autowired
    private ShoppingCartProductRepo shoppingCartProductRepo;

    @Autowired
    private ProductService productService;

    //let's call the unimplemented methods.
    @Override
    public ShoppingCart addToShoppingCart(AddToShoppingCartRequest request, String jwt) throws Exception {

        //first let's find the user.
        User user = userService.findByJwtToken(jwt);

        //now lets find the product.
        Product product = productService.findProductById(request.getProductId());

        //now we should find the shopping cart.
        ShoppingCart shoppingCart = shoppingCartRepo.findByCustomerId(user.getId());

        //now we need to create shopping cart product,
        //first lets check if it is already exists in customer's shopping cart we update it.
        for (ShoppingCartProduct shoppingCartProduct : shoppingCart.getItems()){

            if (shoppingCartProduct.getProduct().getId().equals(product.getId())) {
                int newQuantity = shoppingCartProduct.getQuantity() + 1;
                return updateShoppingCart(shoppingCartProduct.getId(), newQuantity);
            }
        }
        //now if it is not exist we add it.
        ShoppingCartProduct newShoppingCartProduct = new ShoppingCartProduct();
        newShoppingCartProduct.setProduct(product);
        newShoppingCartProduct.setCart(shoppingCart);
        newShoppingCartProduct.setQuantity(request.getQuantity());
        newShoppingCartProduct.setIngredients(request.getIngredients());
        newShoppingCartProduct.setTotalPrice(request.getQuantity() * product.getPrice());

        //now let's save it.
        shoppingCartProductRepo.save(newShoppingCartProduct);
        return shoppingCart;
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
