package com.example.backend.Repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.example.backend.Model.Recipe;

@Repository
public interface RecipeRepository extends JpaRepository<Recipe, Long> {
  @Query("SELECT r FROM Recipe r WHERE r.name = :name AND r.servings = :servings")
  Optional<Recipe> findByNameAndServings(
      @Param("name") String name, @Param("servings") int servings);
}
