package com.rkerkides.recipewebapp.controller;

import com.rkerkides.recipewebapp.entity.Ingredient;
import com.rkerkides.recipewebapp.service.IngredientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/ingredients")
public class IngredientController {

    @Autowired
    private IngredientService ingredientService;

    // Read: Get all ingredients
    @GetMapping
    public ResponseEntity<List<Ingredient>> getIngredients() {
        List<Ingredient> ingredients = ingredientService.findAll();
        return new ResponseEntity<>(ingredients, HttpStatus.OK);
    }

    // Read: Get a single ingredient by ID
    @GetMapping("/{id}")
    public ResponseEntity<?> getIngredientById(@PathVariable Long id) {
        Optional<Ingredient> optionalIngredient = ingredientService.findByID(id);

        if (optionalIngredient.isPresent()) {
            return new ResponseEntity<>(optionalIngredient.get(), HttpStatus.OK);
        } else {
            return new ResponseEntity<>("Ingredient not found", HttpStatus.NOT_FOUND);
        }
    }


    // Create: Add a new ingredient
    @PostMapping
    public ResponseEntity<Ingredient> addIngredient(@RequestBody Ingredient ingredient) {
        Ingredient newIngredient = ingredientService.save(ingredient);
        return new ResponseEntity<>(newIngredient, HttpStatus.CREATED);
    }

    // Update: Update an existing ingredient
    @PutMapping("/{id}")
    public ResponseEntity<Ingredient> updateIngredient(@PathVariable Long id, @RequestBody Ingredient ingredient) {
        ingredient.setId(id);
        Ingredient updatedIngredient = ingredientService.save(ingredient);
        return new ResponseEntity<>(updatedIngredient, HttpStatus.OK);
    }

    // Delete: Delete an ingredient by ID
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteIngredient(@PathVariable Long id) {
        ingredientService.deleteById(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
