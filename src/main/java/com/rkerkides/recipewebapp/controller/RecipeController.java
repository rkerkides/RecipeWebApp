package com.rkerkides.recipewebapp.controller;

import com.rkerkides.recipewebapp.entity.Recipe;
import com.rkerkides.recipewebapp.service.RecipeService;
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
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@RestController
@RequestMapping("/recipes")
public class RecipeController {

    @Autowired
    private RecipeService recipeService;

    @GetMapping
    public ResponseEntity<List<Recipe>> getRecipes() {
        List<Recipe> recipes = recipeService.findAll();
        return new ResponseEntity<>(recipes, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getRecipeById(@PathVariable Long id) {
        Optional<Recipe> optionalRecipe = recipeService.findById(id);

        if (optionalRecipe.isPresent()) {
            return new ResponseEntity<>(optionalRecipe.get(), HttpStatus.OK);
        } else {
            return new ResponseEntity<>("Recipe not found", HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping
    public ResponseEntity<Recipe> addRecipe(@RequestBody Recipe recipe) {
        Recipe newRecipe = recipeService.save(recipe);
        return new ResponseEntity<>(newRecipe, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Recipe> updateRecipe(@PathVariable Long id, @RequestBody Recipe recipe) {
        recipe.setRecipeID(id);
        Recipe updatedRecipe = recipeService.save(recipe);
        return new ResponseEntity<>(updatedRecipe, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteRecipe(@PathVariable Long id) {
        recipeService.deleteById(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @GetMapping("/image/{id}")
    public ResponseEntity<byte[]> getImage(@PathVariable Long id) {
        Optional<Recipe> optionalRecipe = recipeService.findById(id);
        if (optionalRecipe.isPresent()) {
            Recipe recipe = optionalRecipe.get();
            try {
                byte[] image = Files.readAllBytes(Paths.get(recipe.getImagePath()));
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
            String path = saveImage(imageFile);
            Optional<Recipe> optionalRecipe = recipeService.findById(id);
            if (optionalRecipe.isPresent()) {
                Recipe recipe = optionalRecipe.get();
                recipe.setImagePath(path);
                recipeService.save(recipe);
                return new ResponseEntity<>(recipe, HttpStatus.OK);
            } else {
                return new ResponseEntity<>("Recipe not found", HttpStatus.NOT_FOUND);
            }
        } catch (Exception e) {
            return new ResponseEntity<>("Failed to upload image", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    private String saveImage(MultipartFile imageFile) throws IOException {
        String fileName = UUID.randomUUID().toString() + "." + FilenameUtils.getExtension(imageFile.getOriginalFilename());
        String dir = System.getProperty("user.dir") + "/uploads/";
        String path = dir + fileName;
        Files.createDirectories(Paths.get(dir));
        Files.write(Paths.get(path), imageFile.getBytes());
        return path;
    }
}
