package com.example.backend.Controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.example.backend.Model.Food;
import com.example.backend.Service.FoodService;

@RestController
@RequestMapping("/api/foods")
public class FoodController {

  @Autowired private FoodService foodService;

  @GetMapping
  public ResponseEntity<?> getAllFoods() {
    try {
      List<Food> foods = foodService.getAllFoods();
      if (foods.isEmpty()) {
        return new ResponseEntity<>("No foods found.", HttpStatus.NOT_FOUND);
      }
      return new ResponseEntity<>(foods, HttpStatus.OK);
    } catch (Exception e) {
      return new ResponseEntity<>(
          "Error fetching foods: " + e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
    }
  }

  @GetMapping("/{id}")
  public ResponseEntity<Food> getFoodById(@PathVariable Long id) {
    try {
      return foodService
          .getFoodById(id)
          .map(food -> new ResponseEntity<>(food, HttpStatus.OK))
          .orElseGet(() -> ResponseEntity.status(HttpStatus.NOT_FOUND).body(new Food()));
    } catch (Exception e) {
      System.err.println("Error fetching food: " + e.getMessage());
      return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(new Food());
    }
  }

  @PostMapping
  public ResponseEntity<?> createFood(@RequestBody Food food) {
    try {
      Food savedFood = foodService.saveFood(food);
      return new ResponseEntity<>(savedFood, HttpStatus.CREATED);
    } catch (Exception e) {
      return new ResponseEntity<>(
          "Error creating food: " + e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
    }
  }

  @DeleteMapping("/{id}")
  public ResponseEntity<?> deleteFood(@PathVariable Long id) {
    try {
      if (foodService.deleteFoodById(id)) {
        return new ResponseEntity<>("Food deleted successfully.", HttpStatus.NO_CONTENT);
      } else {
        return new ResponseEntity<>("Food with ID " + id + " not found.", HttpStatus.NOT_FOUND);
      }
    } catch (Exception e) {
      return new ResponseEntity<>(
          "Error deleting food: " + e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
    }
  }
}
