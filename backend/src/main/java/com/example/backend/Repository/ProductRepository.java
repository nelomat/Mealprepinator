package com.example.backend.Repository;

import com.example.backend.Model.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {
  Optional<Product> findByNameAndBrandName(String name, String brandName);
}
;
