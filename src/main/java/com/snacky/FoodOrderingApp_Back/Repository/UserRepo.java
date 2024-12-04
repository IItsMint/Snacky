package com.snacky.FoodOrderingApp_Back.Repository;

import com.snacky.FoodOrderingApp_Back.Model.User.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface UserRepo extends JpaRepository<User, Long> {
    //type of the @Id annotation is long so we used above there.

   //let's add custom query methods for the best practice,
   Optional<User> findByEmail(String email); //to handle null values, we used optional

    List<User> findByFirstName(String firstName);
    List<User> findByLastName(String lastName);
    List<User> findByFirstNameAndLastName(String firstName, String lastName); // Combined search

    Page<User> findByFirstName(String firstName, Pageable pageable); // Pagination
    Page<User> findByLastName(String lastName, Pageable pageable); // Pagination
    Page<User> findByFirstNameAndLastName(String firstName, String lastName, Pageable pageable); // Pagination

}
