package com.example.backend.Controller;

import com.example.backend.Model.Food;
import com.example.backend.Service.FoodService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/foods")
public class FoodController {

  @Autowired
  private FoodService foodService;

  @GetMapping
  public List<Food> getAllFoods() {
    return foodService.getAllFoods();
  }

  @GetMapping("/{id}")
  public ResponseEntity<Food> getFoodtById(@PathVariable Long id) {
    return foodService.getFoodById(id)
      .map(ResponseEntity::ok)
      .orElse(ResponseEntity.notFound().build());
  }

  @PostMapping
  public ResponseEntity<Food> createFood(@RequestBody Food food) {
    Food savedFood = foodService.saveFood(food);
    return new ResponseEntity<>(savedFood, HttpStatus.CREATED);
  }

  @DeleteMapping("/{id}")
  public ResponseEntity<Void> deleteFood(@PathVariable Long id) {
    if (foodService.deleteFoodById(id)) {
      return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    } else {
      return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
  }
}
