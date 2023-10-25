package com.rkerkides.recipewebapp.service;

import com.rkerkides.recipewebapp.entity.Ingredient;
import com.rkerkides.recipewebapp.repository.IngredientRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class IngredientService {

    @Autowired
    private IngredientRepository ingredientRepository;

    public List<Ingredient> findAll() { return ingredientRepository.findAll(); }

    public Optional<Ingredient> findByID(Long id) { return ingredientRepository.findById(id); }

    public Ingredient save(Ingredient ingredient) { return ingredientRepository.save(ingredient); }

    public void deleteById(Long id) { ingredientRepository.deleteById(id); }
}
