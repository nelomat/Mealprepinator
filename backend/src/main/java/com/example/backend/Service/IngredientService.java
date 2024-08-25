package com.example.backend.Service;

import com.example.backend.Model.Ingredient;
import com.example.backend.Repository.IngredientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class IngredientService {

  @Autowired
  private IngredientRepository ingredientRepository;

  public List<Ingredient> getAllIngredients() {
    return ingredientRepository.findAll();
  }

  public Optional<Ingredient> getIngredientById(Long id) {
    return ingredientRepository.findById(id);
  }

  public Ingredient saveIngredient(Ingredient ingredient) {
    return ingredientRepository.save(ingredient);
  }

  public boolean deleteIngredientById(Long id) {
    if (ingredientRepository.existsById(id)) {
      ingredientRepository.deleteById(id);
      return true;
    } else {
      return false;
    }
  }

  /*public Ingredient updateIngredient(Long id, Ingredient ingredientDetails) {

    return ingredientRepository.findById(id).map(ingredient -> {
      ingredient.setName(ingredientDetails.getName());
      return ingredientRepository.save(ingredient);
    }).orElse(null);
  } */

}
