package com.snacky.FoodOrderingApp_Back.Service;

import com.snacky.FoodOrderingApp_Back.Dto.CreateProductRequest;
import com.snacky.FoodOrderingApp_Back.Model.Product.Category;
import com.snacky.FoodOrderingApp_Back.Model.Product.Product;
import com.snacky.FoodOrderingApp_Back.Model.Restaurant.Restaurant;

import java.util.List;

public interface ProductService {

    public Product addProduct(CreateProductRequest request, Category category, Restaurant restaurant);

    public List<Product> getAllProducts(Long restaurantId, boolean isVegan, boolean isSeasonal, String productCategory);

    public List<Product> searchProduct(String keyword);

    public Product findProductById(Long id) throws Exception;

    public Product updateStatus(Long productId) throws Exception;

    void deleteProduct(Long productId) throws Exception;
}
