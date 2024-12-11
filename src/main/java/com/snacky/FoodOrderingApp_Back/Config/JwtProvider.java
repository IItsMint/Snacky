package com.snacky.FoodOrderingApp_Back.Config;

import io.jsonwebtoken.JwtBuilder;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.stereotype.Service;

import javax.crypto.SecretKey;
import java.util.*;

@Service
public class JwtProvider {

    private SecretKey key = Keys.hmacShaKeyFor(JwtConstant.JWT_SECRET.getBytes());

    public String generateToken(Authentication auth) {

        //first collect the role from authentication.
        Collection<? extends GrantedAuthority> authorities = auth.getAuthorities();
        String roles = populateAuthorities(authorities);

        String jwt = Jwts.builder().setIssuedAt(new Date()).setExpiration(new Date(new Date().getTime() + 86400000))//after 24 hours it is expiring.
                .claim("email", auth.getName()).claim("authorities", roles).signWith(key).compact();
        return jwt;
    }

    private String populateAuthorities(Collection<? extends GrantedAuthority> authorities) {
        Set<String> auths = new HashSet<>();
        for(GrantedAuthority authority:authorities){
            auths.add(authority.getAuthority());
        }
        return String.join(",", auths);
    }
}
