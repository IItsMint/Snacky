package com.snacky.FoodOrderingApp_Back.Model.Product;

import com.snacky.FoodOrderingApp_Back.Model.Restaurant.Restaurant;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String name;
    private String description;
    private boolean isVegan;
    private boolean isSeasonal;
    private boolean availability;
    private Long price;
    private int stock;
    private Date listingDate;

    @ManyToMany //inside one food, there will be multiple ingredients.
    private List<Ingredients> ingredients = new ArrayList<>();

    @Column(length = 1000) //urls will be long hence we defined them 1000.
    @ElementCollection //it will create separate table for images.
    private List<String> images;

    @ManyToOne
    private Category category;

    @ManyToOne //one restaurant can have more than one product.
    private Restaurant restaurant;

}
