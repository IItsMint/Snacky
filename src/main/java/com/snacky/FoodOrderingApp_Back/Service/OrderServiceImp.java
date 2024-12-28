package com.snacky.FoodOrderingApp_Back.Service;

import com.snacky.FoodOrderingApp_Back.Dto.OrderRequest;
import com.snacky.FoodOrderingApp_Back.Model.Address.Address;
import com.snacky.FoodOrderingApp_Back.Model.Order.Order;
import com.snacky.FoodOrderingApp_Back.Model.Order.OrderProduct;
import com.snacky.FoodOrderingApp_Back.Model.Restaurant.Restaurant;
import com.snacky.FoodOrderingApp_Back.Model.ShoppingCart.ShoppingCart;
import com.snacky.FoodOrderingApp_Back.Model.ShoppingCart.ShoppingCartProduct;
import com.snacky.FoodOrderingApp_Back.Model.User.User;
import com.snacky.FoodOrderingApp_Back.Repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class OrderServiceImp implements OrderService {

    //let's wire the needed instances.
    @Autowired
    private OrderRepo orderRepo;

    @Autowired
    private OrderProductRepo orderProductRepo;

    @Autowired
    private AddressRepo addressRepo;

    @Autowired
    private UserRepo userRepo;

    @Autowired
    private RestaurantService restaurantService;

    @Autowired
    private ShoppingCartService shoppingCartService;



    //let's call unimplemented methods from the signature.
    @Override
    public Order createOrder(OrderRequest orderRequest, User user) throws Exception {

        Address deliveryAddress = orderRequest.getDelivryAddress();
        Address savedAddress = addressRepo.save(deliveryAddress);

        //this is for the user don't give his/her address every single time.
        //adds the address to the user's list if it's new
        if(!user.getAddresses().contains(savedAddress)) {
            user.getAddresses().add(savedAddress);
            userRepo.save(user);
        }

        // Fetches the restaurant to associate with the order.
        Restaurant restaurant = restaurantService.findRestaurantById(orderRequest.getRestaurantId());

        //Creates a new Order object
        Order newOrder = new Order();
        newOrder.setCustomer(user);
        newOrder.setOrderDate(new Date());
        newOrder.setOrderStatus("PENDING");
        newOrder.setDeliveryAddress(savedAddress);
        newOrder.setRestaurant(restaurant);

        //let's find the shopping cart,
        ShoppingCart shoppingCart = shoppingCartService.findShoppingCartByUserId(user.getId());

        //after finding shopping cart, let's find the products in that shopping cart.
        List<OrderProduct> orderProducts = new ArrayList<>();

        //Converts ShoppingCartProduct items into OrderProduct items, saving each item to the database.
        // This ensures a decoupling of the order data from the shopping cart.
        for (ShoppingCartProduct shoppingCartProduct : shoppingCart.getProducts()) {

            //first let's create the order product,
            OrderProduct orderProduct = new OrderProduct();
            orderProduct.setProduct(shoppingCartProduct.getProduct());
            orderProduct.setIngredients(shoppingCartProduct.getIngredients());
            orderProduct.setQuantity(shoppingCartProduct.getQuantity());
            orderProduct.setPrice(shoppingCartProduct.getTotalPrice());

            //let's save it.
            OrderProduct savedOrderProduct = orderProductRepo.save(orderProduct);
            //after saving we need to add this to order items.
            orderProducts.add(savedOrderProduct);
        }

        //Links all the saved OrderProduct items to the order and calculate total price.
        newOrder.setProducts(orderProducts);
        newOrder.setTotalPrice(shoppingCart.getTotal());

        Order savedOrder = orderRepo.save(newOrder);

        //after saving we need to add this order to restaurant as well,
        restaurant.getOrders().add(savedOrder);

        return newOrder;
    }

    @Override
    public Order updateOrder(Long orderId, String orderStatus) throws Exception {

        //first let's find the order,
        Order order = getOrderById(orderId);

        //if any one of the condition true we need to change it.
        if(orderStatus.equals("ON_THE_WAY") || order.getOrderStatus().equals("DELIVERED") ||
                order.getOrderStatus().equals("COMPLETED") || order.getOrderStatus().equals("PENDING")) {

            order.setOrderStatus(orderStatus);
            return orderRepo.save(order);
        }

        throw new Exception("please select a valid order status");
    }

    @Override
    public Order getOrderById(Long id) throws Exception {

        Optional<Order> optionalOrder = orderRepo.findById(id);

        if(optionalOrder.isEmpty()) {
            throw new Exception("Order not found");
        }
        return optionalOrder.get();
    }

    @Override
    public void cancelOrder(Long orderId) throws Exception {

        //first let's find the order,
        Order order = getOrderById(orderId);
        orderRepo.deleteById(orderId);
    }

    @Override
    public List<Order> getUsersOrder(Long userId) throws Exception {
        return orderRepo.findByCustomerId(userId);
    }

    @Override
    public List<Order> getAllOrdersRestaurant(Long restaurantId, String orderStatus) throws Exception {

        //Fetch all orders for the given restaurant
        List<Order> orders = orderRepo.findByRestaurantId(restaurantId);

        //If a specific orderStatus is provided (not null), filter the orders by that status
        if (orderStatus != null){
            orders = orders.stream().filter(order ->
                    order.getOrderStatus().equals(orderStatus)).collect(Collectors.toList());
        }
        return orders;
    }
}
