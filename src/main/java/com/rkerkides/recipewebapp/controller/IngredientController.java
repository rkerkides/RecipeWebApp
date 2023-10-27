package com.rkerkides.recipewebapp.controller;

import com.rkerkides.recipewebapp.entity.Ingredient;
import com.rkerkides.recipewebapp.service.IngredientService;
import org.apache.commons.io.FilenameUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.UUID;

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
        ingredient.setIngredientID(id);
        Ingredient updatedIngredient = ingredientService.save(ingredient);
        return new ResponseEntity<>(updatedIngredient, HttpStatus.OK);
    }

    // Delete: Delete an ingredient by ID
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteIngredient(@PathVariable Long id) {
        ingredientService.deleteById(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @GetMapping("/image/{id}")
    public ResponseEntity<byte[]> getImage(@PathVariable Long id) {
        Optional<Ingredient> optionalIngredient = ingredientService.findByID(id);
        if (optionalIngredient.isPresent()) {
            Ingredient ingredient = optionalIngredient.get();
            try {
                byte[] image = Files.readAllBytes(Paths.get(ingredient.getImagePath()));
                return ResponseEntity.ok().contentType(MediaType.IMAGE_JPEG).body(image);
            } catch (IOException e) {
                return new ResponseEntity<>(new byte[0], HttpStatus.INTERNAL_SERVER_ERROR);
            }
        } else {
            return new ResponseEntity<>(new byte[0], HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping("/{id}/upload")
    public ResponseEntity<?> uploadImage(@PathVariable Long id, @RequestParam("image") MultipartFile imageFile) {
        try {
            // Save image to the file system
            String path = saveImage(imageFile);

            // Update the ingredient entity
            Optional<Ingredient> optionalIngredient = ingredientService.findByID(id);
            if (optionalIngredient.isPresent()) {
                Ingredient ingredient = optionalIngredient.get();
                ingredient.setImagePath(path);
                ingredientService.save(ingredient);
                return new ResponseEntity<>(ingredient, HttpStatus.OK);
            } else {
                return new ResponseEntity<>("Ingredient not found", HttpStatus.NOT_FOUND);
            }
        } catch (Exception e) {
            return new ResponseEntity<>("Failed to upload image", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    private String saveImage(MultipartFile imageFile) throws IOException {
        // Generate a random UUID for the file name to avoid collisions
        String fileName = UUID.randomUUID().toString() + "." + FilenameUtils.getExtension(imageFile.getOriginalFilename());

        // Best practice: Store images in a dedicated directory outside of the source code
        String dir = System.getProperty("user.dir") + "/uploads/";
        String path = dir + fileName;

        // Create directory if it doesn't exist
        Files.createDirectories(Paths.get(dir));

        // Save the image
        Files.write(Paths.get(path), imageFile.getBytes());

        return path;
    }
}
