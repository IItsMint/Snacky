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
        return null;
    }

    @Override
    public List<Product> getAllProducts(Long restaurantId, boolean isVegan, boolean isSeasonal, String productCategory) {
        return List.of();
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

    }
}
