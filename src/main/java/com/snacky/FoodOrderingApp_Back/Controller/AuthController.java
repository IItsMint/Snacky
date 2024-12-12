package com.snacky.FoodOrderingApp_Back.Controller;

import com.snacky.FoodOrderingApp_Back.Config.AuthResponse;
import com.snacky.FoodOrderingApp_Back.Config.JwtProvider;
import com.snacky.FoodOrderingApp_Back.Model.ShoppingCart.ShoppingCart;
import com.snacky.FoodOrderingApp_Back.Model.User.User;
import com.snacky.FoodOrderingApp_Back.Repository.ShoppingCartRepo;
import com.snacky.FoodOrderingApp_Back.Repository.UserRepo;
import com.snacky.FoodOrderingApp_Back.Service.UserDetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;

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
    @PostMapping("/signup")
    public ResponseEntity<AuthResponse> createUserHandler(@RequestBody User user) throws Exception {

       Optional <User> isEmailExist = userRepo.findByEmail(user.getEmail());

       //let's check first if user exists in database.
       if(isEmailExist.isPresent()){
           throw new IllegalArgumentException("This Email address already in use.");
       }

       //if not, create new user.
           User createUser = new User();
           createUser.setEmail(user.getEmail());
           createUser.setPassword(passwordEncoder.encode(user.getPassword())); //this is the form of bcrypt;
           createUser.setFirstName(user.getFirstName());
           createUser.setLastName(user.getLastName());
           createUser.setRole(user.getRole());

          User saveUser = userRepo.save(createUser);// this is saving to db.

           ShoppingCart shoppingCart = new ShoppingCart(); //we need to create a cart for user.
           shoppingCart.setCustomer(saveUser);
           shoppingCartRepo.save(shoppingCart);

           Authentication auth = new UsernamePasswordAuthenticationToken(user.getEmail(), user.getPassword());
           SecurityContextHolder.getContext().setAuthentication(auth);
           String jwt = jwtProvider.generateToken(auth);

           AuthResponse authResponse = new AuthResponse();
           authResponse.setJwt(jwt);
           authResponse.setMessage("Successfully registered.");
           authResponse.setRole(saveUser.getRole());

        return new ResponseEntity<>(authResponse, HttpStatus.CREATED);
    }

}
