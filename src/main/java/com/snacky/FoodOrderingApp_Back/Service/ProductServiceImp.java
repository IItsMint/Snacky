package com.snacky.FoodOrderingApp_Back.Service;

import com.snacky.FoodOrderingApp_Back.Dto.CreateProductRequest;
import com.snacky.FoodOrderingApp_Back.Model.Product.Category;
import com.snacky.FoodOrderingApp_Back.Model.Product.Product;
import com.snacky.FoodOrderingApp_Back.Model.Restaurant.Restaurant;
import com.snacky.FoodOrderingApp_Back.Repository.ProductRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductServiceImp implements ProductService {

    @Autowired
    private ProductRepo productRepo;

    //Let's implement unimplemented methods from the interface.
    @Override
    public Product addProduct(CreateProductRequest request, Category category, Restaurant restaurant) {

        // Let's validate inputs first
        if (request == null || category == null || restaurant == null) {
            throw new IllegalArgumentException("Request, category, and restaurant must not be null.");
        }

        Product product = new Product();

        // Directly passed as a method parameter. Since they are already fetched while creating.
        product.setProductCategory(category);
        product.setRestaurant(restaurant);

        //these are coming from the dto so we are using request.
        product.setName(request.getName());
        product.setDescription(request.getDescription());
        product.setPrice(request.getPrice());
        product.setIngredients(request.getIngredients());
        product.setImages(request.getImage());
        product.setVegan(request.isVegan());
        product.setSeasonal(request.isSeasonal());

        //save product to db.
        Product savedProduct = productRepo.save(product);

        // Add product to the restaurant's product list
        if (restaurant.getProduct() != null) {
            restaurant.getProduct().add(savedProduct);
        }

        return savedProduct;
    }

    @Override
    public List<Product> getAllProducts(Long restaurantId, boolean isVegan, boolean isSeasonal, String productCategory) {


    }

    @Override
    public List<Product> searchProduct(String keyword) {
        return List.of();
    }

    @Override
    public Product findProductById(Long id) throws Exception {
        return null;
    }

    @Override
    public Product updateStatus(Long productId) throws Exception {
        return null;
    }

    @Override
    public void deleteProduct(Long productId) throws Exception {

        Product product = productRepo.findById(productId)
                .orElseThrow(() -> new Exception("Product not found with id: " + productId));

        productRepo.delete(product);
    }

}
