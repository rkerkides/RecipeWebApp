package com.rkerkides.recipewebapp.initializer;

import com.rkerkides.recipewebapp.entity.Ingredient;
import jakarta.annotation.PostConstruct;
import jakarta.persistence.Column;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import com.rkerkides.recipewebapp.service.IngredientService;

@Component
public class IngredientInitializer {

    @Autowired
    private IngredientService ingredientService;

    @PostConstruct
    public void initData() {
        // Create and save the ingredients
        Ingredient ingredient1 = new Ingredient();
        ingredient1.setName("Apple");
        ingredient1.setPricePerKg(1.99);
        ingredient1.setImagePath("uploads/apple.png");
        ingredientService.save(ingredient1);

        Ingredient ingredient2 = new Ingredient();
        ingredient2.setName("Sample Ingredient 2");
        ingredientService.save(ingredient2);

    }


}
