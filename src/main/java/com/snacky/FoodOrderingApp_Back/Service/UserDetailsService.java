package com.snacky.FoodOrderingApp_Back.Service;


import com.snacky.FoodOrderingApp_Back.Model.User.User;
import com.snacky.FoodOrderingApp_Back.Model.User.UserRoles;
import com.snacky.FoodOrderingApp_Back.Repository.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class UserDetailsService implements org.springframework.security.core.userdetails.UserDetailsService {

    //we need to access repository layer so we called here and wired it.
    @Autowired
    private UserRepo userRepo;


    //let's implement unimplemented methods of the UserDetailsService interface.
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        //let's check user exists.
        User user = userRepo.findByEmail(username)
                .orElseThrow(() -> new UsernameNotFoundException("User not found with this email: " + username));

        //let's get the role of the user (we defined default role in entity).
        UserRoles role = user.getRole();
        //let's define empty granted authority.
        List<GrantedAuthority> authorities = new ArrayList<>();
        //let's add to granted list.
        authorities.add(new SimpleGrantedAuthority(role.toString()));

        return new org.springframework.security.core.userdetails.User(user.getEmail(), user.getPassword(), authorities);
    }
}
