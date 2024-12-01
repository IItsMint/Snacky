package com.snacky.FoodOrderingApp_Back.Model.ShoppingCart;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.snacky.FoodOrderingApp_Back.Model.Product.Product;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class CartItem {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @JsonIgnore //whenever we fetch the cart items we don't need cart.
    @ManyToOne
    private ShoppingCart ShoppingCart;

    @ManyToOne //many cart items can have same product.
    private Product product;

    @ElementCollection
    private List<String> ingredients;
    private int quantity;
    private Long totalPrice;
}
