package com.snacky.FoodOrderingApp_Back.Dto;

import lombok.Data;

@Data
public class LoginRequest {
    private String email;
    private String password;
}