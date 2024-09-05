package com.example.backend.Repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.backend.Model.Product;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {
  Optional<Product> findByNameAndBrandName(String name, String brandName);
}
;
