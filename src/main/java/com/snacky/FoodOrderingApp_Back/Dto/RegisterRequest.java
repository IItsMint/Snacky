package com.snacky.FoodOrderingApp_Back.Dto;

import com.snacky.FoodOrderingApp_Back.Model.User.UserRoles;
import lombok.Data;

@Data
public class RegisterRequest {
    private String firstName;
    private String lastName;
    private String email;
    private String password;
    private String phoneNumber;
    private UserRoles role;
}
