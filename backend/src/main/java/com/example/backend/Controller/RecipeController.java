package com.example.backend.Controller;

import com.example.backend.Model.Recipe;
import com.example.backend.Service.RecipeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/recipes")
public class RecipeController {

  @Autowired private RecipeService recipeService;

  @GetMapping
  public ResponseEntity<?> getAllRecipes() {
    try {
      List<Recipe> recipes = recipeService.getAllRecipes();
      if (recipes.isEmpty()) {
        return new ResponseEntity<>("No recipes found.", HttpStatus.NOT_FOUND);
      }
      return new ResponseEntity<>(recipes, HttpStatus.OK);
    } catch (Exception e) {
      return new ResponseEntity<>(
          "Error retrieving recipes: " + e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
    }
  }

  @GetMapping("/{id}")
  public ResponseEntity<Recipe> getRecipeById(@PathVariable Long id) {
    try {
      return recipeService
          .getRecipeById(id)
          .map(ResponseEntity::ok)
          .orElseGet(() -> ResponseEntity.status(HttpStatus.NOT_FOUND).build());
    } catch (Exception e) {
      return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
    }
  }

  @PostMapping
  public ResponseEntity<?> createRecipe(@RequestBody Recipe recipe) {
    try {
      if (recipeService.checkIfRecipeAlreadyExists(recipe.getName(), recipe.getServings())) {
        return new ResponseEntity<>(
            "Recipe already exists with the same name and servings", HttpStatus.BAD_REQUEST);
      }
      Recipe createdRecipe = recipeService.saveRecipe(recipe);
      return new ResponseEntity<>(createdRecipe, HttpStatus.CREATED);
    } catch (Exception e) {
      return new ResponseEntity<>(
          "Error creating recipe: " + e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
    }
  }

  @DeleteMapping("/{id}")
  public ResponseEntity<?> deleteRecipe(@PathVariable Long id) {
    try {
      if (recipeService.getRecipeById(id).isEmpty()) {
        return new ResponseEntity<>("Recipe with ID " + id + " not found.", HttpStatus.NOT_FOUND);
      }
      recipeService.deleteRecipe(id);
      return new ResponseEntity<>("Recipe successfully deleted.", HttpStatus.NO_CONTENT);
    } catch (Exception e) {
      return new ResponseEntity<>(
          "Error deleting recipe with ID " + id + ": " + e.getMessage(),
          HttpStatus.INTERNAL_SERVER_ERROR);
    }
  }
}
