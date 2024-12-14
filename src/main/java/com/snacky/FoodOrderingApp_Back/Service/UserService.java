package com.snacky.FoodOrderingApp_Back.Service;

import com.snacky.FoodOrderingApp_Back.Model.User.User;

public interface UserService {

    public User findByJwtToken(String jwt) throws Exception;
    public User findByEmail(String email) throws Exception;
}
