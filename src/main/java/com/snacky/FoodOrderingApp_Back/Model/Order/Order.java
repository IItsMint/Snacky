package com.snacky.FoodOrderingApp_Back.Model.Order;

import com.snacky.FoodOrderingApp_Back.Model.User.User;
import jakarta.persistence.*;

@Entity
public class Order {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @ManyToOne
    private User customer;

}
