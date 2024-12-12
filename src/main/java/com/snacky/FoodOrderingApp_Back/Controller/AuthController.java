package com.snacky.FoodOrderingApp_Back.Controller;

import com.snacky.FoodOrderingApp_Back.Config.AuthResponse;
import com.snacky.FoodOrderingApp_Back.Config.JwtProvider;
import com.snacky.FoodOrderingApp_Back.Model.User.User;
import com.snacky.FoodOrderingApp_Back.Repository.ShoppingCartRepo;
import com.snacky.FoodOrderingApp_Back.Repository.UserRepo;
import com.snacky.FoodOrderingApp_Back.Service.UserDetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
public class AuthController {

    @Autowired
    private UserRepo userRepo;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private JwtProvider jwtProvider;

    @Autowired
    private UserDetailsService userDetailsService;

    @Autowired
    private ShoppingCartRepo shoppingCartRepo;


    //let's write sign up method.
    public ResponseEntity<AuthResponse> createUserHandler(@RequestBody User user){
        return null;
    }

}
