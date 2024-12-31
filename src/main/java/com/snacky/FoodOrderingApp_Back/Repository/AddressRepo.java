package com.snacky.FoodOrderingApp_Back.Repository;

import com.snacky.FoodOrderingApp_Back.Model.Address.Address;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AddressRepo extends JpaRepository<Address, Long> {

    //we can add custom query methods if it is needed.
}
