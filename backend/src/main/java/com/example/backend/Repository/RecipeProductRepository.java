package com.example.backend.Repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.backend.Model.RecipeProduct;

public interface RecipeProductRepository extends JpaRepository<RecipeProduct, Long> {}
