package com.snacky.FoodOrderingApp_Back.Config;

import io.jsonwebtoken.Claims;
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

        //we are converting the granted authority to string, we will ve converting again to granted authority in service.
        //since jwt can not allow use storing in that format we are changing it types twice.
        String roles = populateAuthorities(authorities);

        String jwt = Jwts.builder().setIssuedAt(new Date()).setExpiration(new Date(new Date().getTime() + 86400000))//after 24 hours it is expiring.
                .claim("email", auth.getName()).claim("authorities", roles).signWith(key).compact();
        return jwt;
    }

    public String getEmailFromToken(String jwt) {

        //again we need to remove the Bearer keyword.
        jwt = jwt.substring(7);

        Claims claims = Jwts.parserBuilder().setSigningKey(key).build().parseClaimsJws(jwt).getBody();
        String email = String.valueOf(claims.get("email"));

        return email;
    }

    private String populateAuthorities(Collection<? extends GrantedAuthority> authorities) {
        Set<String> auths = new HashSet<>();
        for(GrantedAuthority authority:authorities){
            auths.add(authority.getAuthority());
        }
        return String.join(",", auths);
    }
}
