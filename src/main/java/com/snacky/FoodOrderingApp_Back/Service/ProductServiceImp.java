package com.snacky.FoodOrderingApp_Back.Service;

import com.snacky.FoodOrderingApp_Back.Dto.CreateProductRequest;
import com.snacky.FoodOrderingApp_Back.Model.Product.Category;
import com.snacky.FoodOrderingApp_Back.Model.Product.Product;
import com.snacky.FoodOrderingApp_Back.Model.Restaurant.Restaurant;
import com.snacky.FoodOrderingApp_Back.Repository.ProductRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

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

        List<Product> products = productRepo.findByRestaurantId(restaurantId);

        if (isVegan) {
            products = filterByVegan(products, isVegan);
        }
        
        if(isSeasonal){
            products = filterBySeasonal(products, isSeasonal);
        }

        if(productCategory != null && !productCategory.isEmpty()) {

            products = filterByCategory(products, productCategory);

        }
        
        return products;
    }

    //let's implement those filter methods,
    //whenever we want to apply filter we need stream.
    private List<Product> filterByCategory(List<Product> products, String productCategory) {

        return products.stream().filter(product -> {
            //if product category is not null, we get the name from front end.
            if(product.getProductCategory() != null){
                    return product.getProductCategory().getName().equals(productCategory);
                }

            return false;
        }).collect(Collectors.toList());
    }


    private List<Product> filterBySeasonal(List<Product> products, boolean isSeasonal) {

        return products.stream().filter(product -> product.isSeasonal() == isSeasonal).collect(Collectors.toList());
    }

    private List<Product> filterByVegan(List<Product> products, boolean isVegan) {

        return products.stream().filter(product -> product.isVegan() == isVegan).collect(Collectors.toList());
    }

    @Override
    public List<Product> searchProduct(String keyword) {

        return productRepo.searchProduct(keyword);
    }

    @Override
    public Product findProductById(Long id) throws Exception {

        //it is optional for either product is present or not.
        Optional<Product> optProduct = productRepo.findById(id);

        if (optProduct.isEmpty()) {
            throw new Exception("Product does not exist.");
        }

        return optProduct.get();
    }

    @Override
    public Product updateStatus(Long productId) throws Exception {

        Product product = findProductById(productId);

        product.setAvailable(!product.isAvailable());

        productRepo.save(product);
        return product;
    }

    @Override
    public void deleteProduct(Long productId) throws Exception {

        Product product = productRepo.findById(productId)
                .orElseThrow(() -> new Exception("Product not found with id: " + productId));

        productRepo.delete(product);
    }

}
