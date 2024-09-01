package com.example.backend.Service;

import com.example.backend.Model.Recipe;
import com.example.backend.Model.RecipeProduct;
import com.example.backend.Repository.RecipeProductRepository;
import com.example.backend.Repository.RecipeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class RecipeService {

  @Autowired private RecipeRepository recipeRepository;

  @Autowired private RecipeProductRepository recipeProductRepository;

  public List<Recipe> getAllRecipes() {
    return recipeRepository.findAll();
  }

  public Recipe saveRecipe(Recipe recipe) {
    for (RecipeProduct rp : recipe.getRecipeProducts()) {
      rp.setRecipe(recipe);
    }
    return recipeRepository.save(recipe);
  }

  public Optional<Recipe> getRecipeById(Long id) {
    return recipeRepository.findById(id);
  }

  public void deleteRecipe(Long id) {
    recipeRepository.deleteById(id);
  }

  public boolean checkIfRecipeAlreadyExists(String name, int servings) {
    return recipeRepository.findByNameAndServings(name, servings).isPresent();
  }

  //
  // RecipeProduct Methods
  //

  public Recipe addRecipeProduct(Long recipeId, RecipeProduct recipeProduct) {
    Recipe recipe =
        recipeRepository
            .findById(recipeId)
            .orElseThrow(() -> new RuntimeException("Recipe not found"));

    recipeProduct.setRecipe(recipe);
    recipeProductRepository.save(recipeProduct);

    // Add RecipeProduct to the recipe's product list if necessary
    recipe.getRecipeProducts().add(recipeProduct);

    return recipeRepository.save(recipe);
  }

  // Remove a RecipeProduct from a Recipe
  public Recipe removeRecipeProduct(Long recipeId, Long recipeProductId) {
    Recipe recipe =
        recipeRepository
            .findById(recipeId)
            .orElseThrow(() -> new RuntimeException("Recipe not found"));

    RecipeProduct recipeProduct =
        recipeProductRepository
            .findById(recipeProductId)
            .orElseThrow(() -> new RuntimeException("RecipeProduct not found"));

    recipe.getRecipeProducts().remove(recipeProduct);
    recipeProductRepository.delete(recipeProduct);

    return recipeRepository.save(recipe);
  }
}
