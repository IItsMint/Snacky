package com.snacky.FoodOrderingApp_Back.Model.Products;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.snacky.FoodOrderingApp_Back.Model.Restaurant.Restaurant;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Ingredients {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String name;
    private boolean stock = true;

    @ManyToOne //many ingredients have same category.
    private  IngredientCategory category;

    @JsonIgnore
    @ManyToOne //one restaurant has multiple ingredients.
    private Restaurant restaurant;

}
