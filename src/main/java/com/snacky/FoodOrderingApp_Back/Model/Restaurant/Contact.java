package com.snacky.FoodOrderingApp_Back.Model.Restaurant;

import jakarta.persistence.Embeddable;
import lombok.Data;


@Data
@Embeddable
public class Contact {

    private String phone;
    private String alternativePhone;
    private String email;
    private String instagram;
    private String facebook;
    private String googleMapsLink;

}
