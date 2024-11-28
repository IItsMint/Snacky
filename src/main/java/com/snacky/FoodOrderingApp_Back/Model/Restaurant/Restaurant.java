package com.snacky.FoodOrderingApp_Back.Model.Restaurant;

import com.snacky.FoodOrderingApp_Back.Model.Address.Address;
import com.snacky.FoodOrderingApp_Back.Model.Order.Order;
import com.snacky.FoodOrderingApp_Back.Model.User.User;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor
public class Restaurant {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @OneToOne//we are gonna provide one restaurant for one owner.
    private User owner;

    private String name;
    private String description;
    private String cuisineType;
    private String workingHours;

    @OneToOne
    private Address address;

    @Embedded
    private Contact contact;

    //with using mapped by, we are telling to spring boot, don't create separate for this,
    // just go to restaurant order table and use the data for mapping this.
    @OneToMany(mappedBy = "restaurant")
    private List<Order> orders = new ArrayList<>();
}
