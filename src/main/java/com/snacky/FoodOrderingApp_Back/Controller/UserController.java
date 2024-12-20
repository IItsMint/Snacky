package com.snacky.FoodOrderingApp_Back.Controller;

import com.snacky.FoodOrderingApp_Back.Model.User.User;
import com.snacky.FoodOrderingApp_Back.Service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/users")//all the endpoint starts from users.
public class UserController {

    @Autowired
    private  UserService userService;

    @GetMapping("/profile")
    public ResponseEntity<User> findByJwtToken(@RequestHeader("Authorization") String jwt) throws Exception {
    User user = userService.findByJwtToken(jwt);
    return new ResponseEntity<>(user, HttpStatus.OK);
    }
}