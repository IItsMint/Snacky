package com.snacky.FoodOrderingApp_Back.Controller;

import com.snacky.FoodOrderingApp_Back.Config.AuthResponse;
import com.snacky.FoodOrderingApp_Back.Config.JwtProvider;
import com.snacky.FoodOrderingApp_Back.Dto.LoginRequest;
import com.snacky.FoodOrderingApp_Back.Dto.RegisterRequest;
import com.snacky.FoodOrderingApp_Back.Model.ShoppingCart.ShoppingCart;
import com.snacky.FoodOrderingApp_Back.Model.User.User;
import com.snacky.FoodOrderingApp_Back.Model.User.UserRoles;
import com.snacky.FoodOrderingApp_Back.Repository.ShoppingCartRepo;
import com.snacky.FoodOrderingApp_Back.Repository.UserRepo;
import com.snacky.FoodOrderingApp_Back.Service.UserDetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;
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
    public ResponseEntity<AuthResponse> createUserHandler(@RequestBody RegisterRequest request) throws Exception {

       Optional <User> isEmailExist = userRepo.findByEmail(request.getEmail());

       //let's check first if user exists in database.
       if(isEmailExist.isPresent()){
           throw new IllegalArgumentException("This Email address already in use.");
       }

       //if not, create new user.
           User createUser = new User();
           createUser.setEmail(request.getEmail());
           createUser.setPassword(passwordEncoder.encode(request.getPassword())); //this is the form of bcrypt;
           createUser.setFirstName(request.getFirstName());
           createUser.setLastName(request.getLastName());
           createUser.setEmail(request.getEmail());

           createUser.setPhoneNumber(request.getPhoneNumber());
           createUser.setRole(request.getRole());

          User saveUser = userRepo.save(createUser);// this is saving to db.

           ShoppingCart shoppingCart = new ShoppingCart(); //we need to create a cart for user.
           shoppingCart.setCustomer(saveUser);
           shoppingCartRepo.save(shoppingCart);

           Authentication auth = new UsernamePasswordAuthenticationToken(request.getEmail(), request.getPassword());
           SecurityContextHolder.getContext().setAuthentication(auth);

           //after authenticating, we need to generate token.
           String jwt = jwtProvider.generateToken(auth);

           //now we need auth response object.
           AuthResponse authResponse = new AuthResponse();
           authResponse.setJwt(jwt);
           authResponse.setMessage("Successfully registered.");
           authResponse.setRole(saveUser.getRole());

        return new ResponseEntity<>(authResponse, HttpStatus.CREATED);
    }

    @PostMapping("/login")//in body we need to provide credentials hence its a post mapping.
    public ResponseEntity<AuthResponse> loginHandler(@RequestBody LoginRequest req) throws Exception {

        String username = req.getEmail();
        String password = req.getPassword();
        Authentication authentication = authenticate(username, password);

        //we also  need to get the authority of the user.
        Collection<? extends GrantedAuthority> authorities = authentication.getAuthorities();
        String role = authorities.isEmpty()?null:authorities.iterator().next().getAuthority();

        //after authenticating, we need to generate token.
        String jwt = jwtProvider.generateToken(authentication);

        //now we need auth response object.
        AuthResponse authResponse = new AuthResponse();
        authResponse.setJwt(jwt);
        authResponse.setMessage("Successfully logged in.");
        authResponse.setRole(UserRoles.valueOf(role));

        return new ResponseEntity<>(authResponse, HttpStatus.OK);
    }

    private Authentication authenticate(String username, String password) {

        UserDetails userDetails = userDetailsService.loadUserByUsername(username);

        if(userDetails == null){
            throw new UsernameNotFoundException("User not found.");
        }

        //if username exists in database now we need to check its password.
        //we can not compare them with == sign hence we are encoding them.
        if(!passwordEncoder.matches(password, userDetails.getPassword())){
            throw new BadCredentialsException("Bad credentials.");
        }

        return new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities());
    }

}
