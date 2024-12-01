package com.snacky.FoodOrderingApp_Back.Model.Products;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.snacky.FoodOrderingApp_Back.Model.Restaurant.Restaurant;
import jakarta.persistence.*;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Entity
@Data
public class IngredientCategory {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String name;

    @JsonIgnore //whenever we fetch ingredientCategory object, we don't need restaurant.
    @ManyToOne //one restaurant can have many ingredient category.
    private Restaurant restaurant;

    @OneToMany(mappedBy = "category", cascade = CascadeType.ALL) //one category have multiple ingredients.
    private List<Ingredients> ingredients = new ArrayList<>();
}
