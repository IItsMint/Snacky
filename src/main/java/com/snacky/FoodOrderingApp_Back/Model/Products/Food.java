package com.snacky.FoodOrderingApp_Back.Model.Products;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Food {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String name;
    private String description;
    private Long price;

    @Column(length = 1000)//urls will be long hence we defined them 1000.
    @ElementCollection //it will create separate table for images.
    private List<String> images;

    @ManyToOne
    private Category category;

}
