package com.snacky.FoodOrderingApp_Back.Repository;

import com.snacky.FoodOrderingApp_Back.Model.Order.OrderProduct;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OrderProductRepo extends JpaRepository<OrderProduct, Long> {

    //we can add custom query methods if it is needed.
}
