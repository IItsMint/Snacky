package com.snacky.FoodOrderingApp_Back.Controller;

import com.snacky.FoodOrderingApp_Back.Model.Order.Order;
import com.snacky.FoodOrderingApp_Back.Model.User.User;
import com.snacky.FoodOrderingApp_Back.Service.OrderService;
import com.snacky.FoodOrderingApp_Back.Service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/admin")
public class AdminOrderController {

    //let's first call and wire the instances that we are gonna use.
    @Autowired
    private OrderService orderService;

    @Autowired
    private UserService userService;

    @GetMapping("/order/restaurant/{id}")
    ResponseEntity<List<Order>> getAllRestaurantOrder(@PathVariable Long id,
                                                      @RequestParam (required = false) String order_status,
                                                      @RequestHeader("Authorization") String jwt)throws Exception{

        //first we need to find the user,
        User user = userService.findByJwtToken(jwt);

        List<Order> orders = orderService.getAllOrdersRestaurant(id, order_status);
        return new ResponseEntity<>(orders, HttpStatus.OK);
    }

    @PutMapping("/order/{id}/{order_status}")
    ResponseEntity<Order> updateOrder(@PathVariable String order_status,
                                            @PathVariable Long id,
                                            @RequestHeader("Authorization")String jwt)throws Exception{

        //first let's find the user,
        User user = userService.findByJwtToken(jwt);

        Order updateOrder = orderService.updateOrder(id, order_status);

        return new ResponseEntity<>(updateOrder, HttpStatus.OK);
    }
}
