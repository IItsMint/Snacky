package com.snacky.FoodOrderingApp_Back.Dto;

import com.snacky.FoodOrderingApp_Back.Model.Address.Address;
import com.snacky.FoodOrderingApp_Back.Model.Restaurant.Contact;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.List;

@Data
public class CreateRestaurantRequest {

    private Long restaurantId;
    private String name;
    private String cuisine;
    private String description;
    private Address address;
    private Contact contactInformation;
    private String workingHours;
    private List<String>images;

}
