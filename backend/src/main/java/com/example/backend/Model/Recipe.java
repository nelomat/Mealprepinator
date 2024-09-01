package com.example.backend.Model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@Entity
public class Recipe {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  private String name;

  private int servings;

  @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
  private List<RecipeProduct> recipeProducts = new ArrayList<>();
}
