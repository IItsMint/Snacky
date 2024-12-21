package com.snacky.FoodOrderingApp_Back.Repository;

import com.snacky.FoodOrderingApp_Back.Model.Product.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductRepo extends JpaRepository<Product, Long> {

    List<Product> findByRestaurantId(Long restaurantId);

    @Query("SELECT f FROM Product f WHERE f.name LIKE %:keyword% OR f.productCategory.name LIKE %:keyword%")
    List<Product> searchProduct(@Param("keyword") String keyword);
}
