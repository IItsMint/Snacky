package com.snacky.FoodOrderingApp_Back.Dto;

import com.snacky.FoodOrderingApp_Back.Model.Address.Address;
import lombok.Data;

@Data
public class OrderRequest {

    private Long restaurantId;
    private Address delivryAddress;
}
