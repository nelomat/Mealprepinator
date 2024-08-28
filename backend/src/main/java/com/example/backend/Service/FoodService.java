package com.example.backend.Service;

import com.example.backend.Model.Food;
import com.example.backend.Repository.FoodRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class FoodService {

  @Autowired
  private FoodRepository foodRepository;

  public List<Food> getAllFoods() {
    return foodRepository.findAll();
  }

  public Optional<Food> getFoodById(Long id) {
    return foodRepository.findById(id);
  }

  public Food saveFood(Food food) {
    return foodRepository.save(food);
  }

  public boolean deleteFoodById(Long id) {
    if (foodRepository.existsById(id)) {
      foodRepository.deleteById(id);
      return true;
    } else {
      return false;
    }
  }

}
