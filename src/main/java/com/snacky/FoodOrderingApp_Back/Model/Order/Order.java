package com.snacky.FoodOrderingApp_Back.Model.Order;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.snacky.FoodOrderingApp_Back.Model.Address.Address;
import com.snacky.FoodOrderingApp_Back.Model.Restaurant.Restaurant;
import com.snacky.FoodOrderingApp_Back.Model.User.User;
import jakarta.persistence.*;

import java.util.Date;

@Entity
public class Order {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @ManyToOne//relation between order and user.
    private User customer;

    @JsonIgnore//whenever, we fetch the order object, we don't need restaurant object inside order object.
    @ManyToOne//relation between order and restaurant.
    private Restaurant restaurant;

    private Long total;
    private String status;
    private Date date;

    @ManyToOne//Relation between order and address.
    private Address address;


}
