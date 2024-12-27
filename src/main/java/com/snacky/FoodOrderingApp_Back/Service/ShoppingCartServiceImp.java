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

import java.util.Optional;

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
    public ShoppingCartProduct addToShoppingCart(AddToShoppingCartRequest request, String jwt) throws Exception {

        //first let's find the user.
        User user = userService.findByJwtToken(jwt);

        //now lets find the product.
        Product product = productService.findProductById(request.getProductId());

        //now we should find the shopping cart.
        ShoppingCart shoppingCart = shoppingCartRepo.findByCustomerId(user.getId());

        //now we need to create shopping cart product,
        //first lets check if it is already exists in customer's shopping cart we update it.
        for (ShoppingCartProduct shoppingCartProduct : shoppingCart.getProducts()){

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
        ShoppingCartProduct savedShoppingCartProduct = shoppingCartProductRepo.save(newShoppingCartProduct);

        //after saving we need to add to shopping cart as well.
        shoppingCart.getProducts().add(savedShoppingCartProduct);

        return savedShoppingCartProduct;
    }

    @Override
    public ShoppingCartProduct updateShoppingCart(Long shoppingCartId, int quantity) throws Exception {

        //first let's find the product that we are gonna update,
        Optional<ShoppingCartProduct> optShoppingCartProduct = shoppingCartProductRepo.findById(shoppingCartId);

        if(optShoppingCartProduct.isEmpty()){
            throw new Exception("Shopping cart  product does not found");
        }

        //if it is not empty, we should have in the database,
        ShoppingCartProduct shoppingCartProduct = optShoppingCartProduct.get();
        shoppingCartProduct.setQuantity(quantity);

        shoppingCartProduct.setTotalPrice(shoppingCartProduct.getProduct().getPrice() * quantity);

        return shoppingCartProductRepo.save(shoppingCartProduct);
    }

    @Override
    public ShoppingCartProduct removeFromShoppingCart(Long shoppingCartItemId, String jwt) throws Exception {

        //first let's find the user.
        User user = userService.findByJwtToken(jwt);

        //now let's find the shopping cart with the user id we just extracted from jwt.
        ShoppingCart shoppingCart = shoppingCartRepo.findByCustomerId(user.getId());

        //now from this cart, we need to remove shopping cart product, so we need to find the shopping cart product.
        Optional<ShoppingCartProduct> optShoppingCartProduct = shoppingCartProductRepo.findById(shoppingCartItemId);
        if(optShoppingCartProduct.isEmpty()){
            throw new Exception("Shopping cart  product does not found");
        }
        ShoppingCartProduct shoppingCartProduct = optShoppingCartProduct.get();

        //now let's remove it.
        shoppingCart.getProducts().remove(shoppingCartProduct);

        // Delete the product (if necessary, depending on cascading and orphan removal)
        shoppingCartProductRepo.delete(shoppingCartProduct);

        return shoppingCartProduct;
    }

    @Override
    public ShoppingCartProduct calculateTotal(ShoppingCart shoppingCart) throws Exception {
        return null;
    }

    @Override
    public ShoppingCartProduct findShoppingCartById(Long id) throws Exception {
        return null;
    }

    @Override
    public ShoppingCartProduct findShoppingCartByUserId(Long userId) throws Exception {
        return null;
    }

    @Override
    public ShoppingCartProduct cancelShoppingCart(Long userId) throws Exception {
        return null;
    }
}
