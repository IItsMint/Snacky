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
    public ShoppingCart removeFromShoppingCart(Long shoppingCartItemId, String jwt) throws Exception {

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

        return shoppingCart;
    }

    @Override
    public Long calculateTotal(ShoppingCart shoppingCart) throws Exception {

        //we used L near the number to become Long value with wrapper, so instead of 32-bit integer it becomes 64 bit long.
        Long total = 0L;

        for (ShoppingCartProduct shoppingCartProduct : shoppingCart.getProducts()) {
            total += shoppingCartProduct.getTotalPrice()*shoppingCartProduct.getQuantity();
        }
        return total;
    }

    @Override
    public ShoppingCart findShoppingCartById(String  jwt) throws Exception {

        User user = userService.findByJwtToken(jwt);

        Optional<ShoppingCart> optShoppingCart = shoppingCartRepo.findById(user.getId());

        if(optShoppingCart.isEmpty()){
            throw new Exception("Shopping cart does not found with this id: "+user.getId());
        }

        return optShoppingCart.get();
    }

    @Override
    public ShoppingCart findShoppingCartByUserId(Long userId) throws Exception {

        ShoppingCart shoppingCart = shoppingCartRepo.findByCustomerId(userId);

        if (shoppingCart == null) {
            throw new Exception("Shopping cart does not found for user with id: " + userId);
        }

        return shoppingCart;
    }

    @Override
    public ShoppingCart cancelShoppingCart(String jwt) throws Exception {

        //first let's find the user.
        User user = userService.findByJwtToken(jwt);

        //than, lets find the shopping cart,
        ShoppingCart shoppingCart = findShoppingCartByUserId(user.getId());

        //let's clear it
        shoppingCart.getProducts().clear();

        //let's save it.
        shoppingCartRepo.save(shoppingCart);

        return shoppingCart;
    }
}
