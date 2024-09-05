package com.example.backend.Model;

import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

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
