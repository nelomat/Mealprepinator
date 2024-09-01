package com.example.backend.Model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@Entity
public class Product {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  private String name;

  @ManyToOne(cascade = CascadeType.PERSIST)
  @JoinColumn(name = "brand_id")
  private Brand brand;

  private int quantity;

  @ManyToOne(cascade = CascadeType.PERSIST)
  @JoinColumn(name = "unit_id")
  private Unit unit;

  @OneToMany(mappedBy = "product", cascade = CascadeType.ALL, orphanRemoval = true)
  private List<ProductFood> foods = new ArrayList<>();

  public Product() {
  }
}
