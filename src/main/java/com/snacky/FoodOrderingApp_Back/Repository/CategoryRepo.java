package com.snacky.FoodOrderingApp_Back.Repository;

import com.snacky.FoodOrderingApp_Back.Model.Product.Category;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CategoryRepo extends JpaRepository<Category, Long> {

    public List<Category> findAllByRestaurantId(Long id);
}
