package com.snacky.FoodOrderingApp_Back.Repository;

import com.snacky.FoodOrderingApp_Back.Model.Restaurant.Restaurant;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RestaurantRepo  extends JpaRepository<Restaurant, Long> {

    //let's search restaurant with all lower cases in database.
    @Query("SELECT r FROM Restaurant r WHERE lower(r.name) LIKE lower(concat('%', :query, '%') ) " +
            "OR lower(r.cuisineType) LIKE lower(concat('%', :query, '%') ) ")
    List<Restaurant> findBySearchQuery(String query);

    Restaurant findByOwnerId(Long userId);

}
