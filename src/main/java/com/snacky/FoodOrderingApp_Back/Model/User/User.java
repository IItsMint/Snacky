package com.snacky.FoodOrderingApp_Back.Model.User;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.snacky.FoodOrderingApp_Back.Dto.RestaurantDto;
import com.snacky.FoodOrderingApp_Back.Model.Address.Address;
import com.snacky.FoodOrderingApp_Back.Model.Order.Order;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.UuidGenerator;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Entity
@Table(name = "snacky_user")
@Data //We have using lombok, hence we don't need to create getters and setters etc.
@AllArgsConstructor
@NoArgsConstructor
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id",nullable = false)
    private Long id;

    @Column(name = "role", nullable = false)
    private UserRoles role;

    @Column(name = "first_name",nullable = false)
    private String firstName;

    @Column(name = "last_name", nullable = false)
    private String lastName;

    @Column(name = "email", nullable = false)
    private String email;

    @Column(name = "password", nullable = false)
    private String password;

    @Column(name = "phone_number",nullable = false)
    private String phoneNumber;

    //relation between user and address.
    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true) //to delete all the addresses with user we need cascade.
    private List<Address> addresses = new ArrayList<>();

    @JsonIgnore //we don't need this list while fetching user.
    //relation between user and orders.
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "customer") //we are not creating new tables every time we used order table.
    private List<Order> orders = new ArrayList<>();

    @ElementCollection
    private List<RestaurantDto> favorites = new ArrayList<>();

    private String status;




}
