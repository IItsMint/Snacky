package com.snacky.FoodOrderingApp_Back.Config;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.crypto.SecretKey;
import java.io.IOException;
import java.util.List;

public class JwtTokenValidator extends OncePerRequestFilter {

    //we are implement the unimplemented method of the OncePerRequest Class.
    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {

        //in here we are requesting header value.
        String jwt = request.getHeader(JwtConstant.JWT_HEADER);

        //requested token comes like Bearer SecretKey.
        //we need substring 7 for skipping Bearer prefix (bearer + space is 7 character).
        if(jwt != null){
            jwt = jwt.substring(7);

            try{
                SecretKey key = Keys.hmacShaKeyFor(JwtConstant.JWT_SECRET.getBytes());
                Claims claims = Jwts.parserBuilder().setSigningKey(key).build().parseClaimsJws(jwt).getBody();

                //we need to extract two things, email and authority.
                String email = String.valueOf(claims.get("email"));
                String authorities = (String) claims.get("authorities");

                //Converts the roles string into a list of GrantedAuthority objects, which are used by Spring Security.
                List<GrantedAuthority> auth = AuthorityUtils.commaSeparatedStringToAuthorityList(authorities);
                Authentication authentication = new UsernamePasswordAuthenticationToken(email, null, auth);
                SecurityContextHolder.getContext().setAuthentication(authentication);
            }
            catch(Exception e){
                throw new BadCredentialsException("Invalid JWT Token...");
            }
        }

        //After processing the token, the request is passed to the next filter
        filterChain.doFilter(request, response);
    }
}
