package com.example.backend.Controller;

import com.example.backend.Model.Recipe;
import com.example.backend.Service.RecipeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;



@RestController
@RequestMapping("/recipes")
public class RecipeController {

  @Autowired
  private RecipeService recipeService;

  @GetMapping
  public ResponseEntity<?> getAllRecipes() {
    try {
      List<Recipe> recipes = recipeService.getAllRecipes();
      if (recipes.isEmpty()) {
        return new ResponseEntity<>("No recipes found. ", HttpStatus.NOT_FOUND);
      }
      return new ResponseEntity<>(recipes, HttpStatus.OK);
    } catch (Exception e) {
      return new ResponseEntity<>("Get recipes Server Error  " + e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
    }
  }


  @PostMapping
  public Recipe createRecipe(@RequestBody Recipe recipe) {
    return recipeService.saveRecipe(recipe);
  }

  @GetMapping("/{id}")
  public ResponseEntity<Recipe> getRecipeById(@PathVariable Long id) {
    return recipeService.getRecipeById(id)
      .map(ResponseEntity::ok)
      .orElse(ResponseEntity.notFound().build());
  }

  @DeleteMapping("/{id}")
  public void deleteRecipe(@PathVariable Long id) {
    recipeService.deleteRecipe(id);
  }
}
