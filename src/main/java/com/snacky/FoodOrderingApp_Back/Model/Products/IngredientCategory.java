package com.snacky.FoodOrderingApp_Back.Model.Products;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Data;

@Entity
@Data
public class IngredientCategory {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
}
