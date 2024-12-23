package com.snacky.FoodOrderingApp_Back.Service;

import com.snacky.FoodOrderingApp_Back.Model.Product.Category;

import java.util.List;

public interface CategoryService {

    //let's first define create category
    //we have id here so that we can find the which restaurant we wanna create the category for.
    public Category createCategory(String categoryName, Long userId) throws Exception;

    public List<Category> findCategoryByRestaurantId(Long id) throws Exception;

    public Category findCategoryById(Long id) throws Exception;

}
