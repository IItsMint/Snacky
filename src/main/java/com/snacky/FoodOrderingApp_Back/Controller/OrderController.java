package com.snacky.FoodOrderingApp_Back.Controller;

import com.snacky.FoodOrderingApp_Back.Dto.OrderRequest;
import com.snacky.FoodOrderingApp_Back.Model.Order.Order;
import com.snacky.FoodOrderingApp_Back.Model.User.User;
import com.snacky.FoodOrderingApp_Back.Service.OrderService;
import com.snacky.FoodOrderingApp_Back.Service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")//all the end points starting from api.
public class OrderController {

    //let's call instances that we are gonna use.

    @Autowired
    private OrderService orderService;

    @Autowired
    private UserService userService;

    @PostMapping("/order")
    public ResponseEntity<Order> createOrder(@RequestBody OrderRequest orderRequest,
                                             @RequestHeader("Authorization")String jwt) throws Exception {

        //first let's find the user,
        User user = userService.findByJwtToken(jwt);

        //now let's create order.
        Order order = orderService.createOrder(orderRequest, user);

        return new ResponseEntity<>(order, HttpStatus.CREATED);
    }

}
