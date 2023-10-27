package com.rkerkides.recipewebapp.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class RecipeIngredient {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long recipeIngredientID;
    private Float quantity;
    private String unit;

    @ManyToOne
    @JoinColumn(name = "recipeID")
    private Recipe recipe;

    @ManyToOne
    @JoinColumn(name = "ingredientID")
    private Ingredient ingredient;
}
