package com.example.backend.Service;

import com.example.backend.Model.Recipe;
import com.example.backend.Repository.RecipeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class RecipeService {

  @Autowired
  private RecipeRepository recipeRepository;

  public List<Recipe> getAllRecipes() {
    return recipeRepository.findAll();
  }

  public Recipe saveRecipe(Recipe recipe) {
    return recipeRepository.save(recipe);
  }

  public Optional<Recipe> getRecipeById(Long id) {
    return recipeRepository.findById(id);
  }

  public void deleteRecipe(Long id) {
    recipeRepository.deleteById(id);
  }
}
