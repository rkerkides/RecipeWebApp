package com.rkerkides.recipewebapp.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;
import java.util.Set;

@Entity
@Getter
@Setter
public class Recipe {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long recipeID;

    private String title;
    private String description;
    private Integer preparationTime;
    private Integer cookingTime;
    private Date datePosted;
    private String imagePath;

    @ManyToOne
    @JoinColumn(name = "userID")
    private User user;

    @OneToMany(mappedBy = "recipe")
    private Set<RecipeIngredient> recipeIngredients;



}
