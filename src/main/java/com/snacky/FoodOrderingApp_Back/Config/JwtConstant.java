package com.snacky.FoodOrderingApp_Back.Config;

public class JwtConstant {

    //Let's create random secret key string.
    public static final String JWT_SECRET = System.getenv("JWT_SECRET");
    public static final String JWT_HEADER = "Authorization";


}
