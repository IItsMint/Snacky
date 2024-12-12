package com.snacky.FoodOrderingApp_Back.Config;

import com.snacky.FoodOrderingApp_Back.Model.User.UserRoles;
import lombok.Data;

@Data
public class AuthResponse {

    private String jwt;
    private String message;
    private UserRoles role;
}
