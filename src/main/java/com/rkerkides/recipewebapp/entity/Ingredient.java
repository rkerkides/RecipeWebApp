package com.rkerkides.recipewebapp.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class Ingredient {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long ingredientID;
    @Column
    private String name;
    @Column
    private Double pricePerKg;
    @Column
    private String imagePath;

}
