package com.rkerkides.recipewebapp.initializer;

import com.rkerkides.recipewebapp.entity.Recipe;
import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import com.rkerkides.recipewebapp.service.RecipeService;

@Component
public class RecipeInitializer {

    @Autowired
    private RecipeService recipeService;

    @PostConstruct
    public void initData() {
        // Create and save the recipes
        for (int i = 1; i <= 15; i++) {
            Recipe recipe = new Recipe();
            recipe.setTitle("Sample Recipe " + i);
            recipe.setDescription("This is a description for Sample Recipe " + i);
            recipe.setImagePath("uploads/" + i + ".png");
            recipeService.save(recipe);
        }
    }
}
