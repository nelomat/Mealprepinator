package com.example.backend.Model;

import jakarta.persistence.*;

@Entity
@Table(name = "recipe_product")
public class RecipeProduct {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @ManyToOne
  @JoinColumn(name = "recipe_id")
  private Recipe recipe;

  @ManyToOne
  @JoinColumn(name = "product_id")
  private Product product;

  private int quantity;  // Menge des Produkts im Rezept

  @ManyToOne
  @JoinColumn(name = "unit_id")
  private Unit unit;  // Einheit des Produkts im Rezept
}
