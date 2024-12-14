package com.snacky.FoodOrderingApp_Back.Service;

import com.snacky.FoodOrderingApp_Back.Config.JwtProvider;
import com.snacky.FoodOrderingApp_Back.Model.User.User;
import com.snacky.FoodOrderingApp_Back.Repository.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImp implements UserService {

    //lets call repo
    @Autowired
    UserRepo userRepo;

    @Autowired
    private JwtProvider jwtProvider;

    //lets call unimplemented methods.
    @Override
    public User findByJwtToken(String jwt) throws Exception {
        //first extract the email from the jwt.
        String email = jwtProvider.getEmailFromToken(jwt);//extracting from token
        User user = findByEmail(email);//searching for finding user with email.
        return user;
    }

    @Override
    public User findByEmail(String email) throws Exception {
            return userRepo.findByEmail(email)
                    .orElseThrow(() -> new Exception("User not found")); // Handle the absence of a user
    }
}
