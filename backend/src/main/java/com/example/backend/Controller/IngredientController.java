package com.example.backend.Controller;

import com.example.backend.Model.Ingredient;
import com.example.backend.Service.IngredientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/ingredients")
public class IngredientController {

  @Autowired
  private IngredientService ingredientService;

  @GetMapping
  public List<Ingredient> getAllIngredients() {
    return ingredientService.getAllIngredients();
  }

  @GetMapping("/{id}")
  public ResponseEntity<Ingredient> getIngredientById(@PathVariable Long id) {
    return ingredientService.getIngredientById(id)
      .map(ResponseEntity::ok)
      .orElse(ResponseEntity.notFound().build());
  }

  @PostMapping
  public ResponseEntity<Ingredient> createIngredient(@RequestBody Ingredient ingredient) {
    Ingredient savedIngredient = ingredientService.saveIngredient(ingredient);
    return new ResponseEntity<>(savedIngredient, HttpStatus.CREATED);
  }

  @DeleteMapping("/{id}")
  public ResponseEntity<Void> deleteIngredient(@PathVariable Long id) {
    if (ingredientService.deleteIngredientById(id)) {
      return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    } else {
      return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
  }

/*  @PutMapping("/{id}")
  public ResponseEntity<Ingredient> updateIngredient(@PathVariable Long id, @RequestBody Ingredient ingredientDetails) {
    Ingredient updatedIngredient = ingredientService.updateIngredient(id, ingredientDetails);
    if (updatedIngredient != null) {
      return new ResponseEntity<>(updatedIngredient, HttpStatus.OK);
    } else {
      return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
  } */

}
