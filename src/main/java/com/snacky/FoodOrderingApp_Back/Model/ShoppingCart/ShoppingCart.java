package com.snacky.FoodOrderingApp_Back.Model.ShoppingCart;

import com.snacky.FoodOrderingApp_Back.Model.User.User;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ShoppingCart {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @OneToOne //one user only have one shopping cart
    private User customer;

    @OneToMany(mappedBy = "CartItem", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<CartItem> items = new ArrayList<>();

    private Long total;


}
