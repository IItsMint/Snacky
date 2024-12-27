package com.snacky.FoodOrderingApp_Back.Service;

import com.snacky.FoodOrderingApp_Back.Dto.OrderRequest;
import com.snacky.FoodOrderingApp_Back.Model.Order.Order;
import com.snacky.FoodOrderingApp_Back.Model.User.User;
import org.aspectj.weaver.ast.Or;

import java.util.List;

public interface OrderService {

    //let's write all the methods signatures for the order service.
    public Order createOrder(OrderRequest orderRequest, User user) throws Exception;
    public Order updateOrder(Long orderId, String orderStatus)throws Exception;
    public Order getOrderById(Long id)throws Exception;
    public void  cancelOrder(Long orderId)throws Exception;
    public List<Order> getUsersOrder(Long userId)throws Exception;
    public List<Order> getAllOrdersRestaurant(Long restaurantId, String orderStatus)throws Exception;
}
