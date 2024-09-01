package com.example.backend.Controller;

import com.example.backend.Model.Product;
import com.example.backend.Service.BrandService;
import com.example.backend.Service.ProductService;
import com.example.backend.Service.UnitService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/products")
public class ProductController {

  @Autowired private final ProductService productService;

  @Autowired private final BrandService brandService;

  @Autowired private final UnitService unitService;

  public ProductController(
      ProductService productService, BrandService brandService, UnitService unitService) {
    this.productService = productService;
    this.brandService = brandService;
    this.unitService = unitService;
  }

  @PostMapping
  public ResponseEntity<?> createProduct(@RequestBody Product product) {
    try {
      if (productService.checkIfProductAlreadyExists(product)) {
        return new ResponseEntity<>(
            "Product with the same name and brand already exists", HttpStatus.CONFLICT);
      }
      Product createdProduct = productService.saveProduct(product);
      return new ResponseEntity<>(createdProduct, HttpStatus.CREATED);
    } catch (Exception e) {
      return new ResponseEntity<>(
          "Error creating product: " + e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
    }
  }

  @GetMapping
  public ResponseEntity<?> getAllProducts() {
    try {
      List<Product> products = productService.getAllProducts();
      if (products.isEmpty()) {
        return new ResponseEntity<>("No products found.", HttpStatus.NOT_FOUND);
      }
      return new ResponseEntity<>(products, HttpStatus.OK);
    } catch (Exception e) {
      return new ResponseEntity<>(
          "Error fetching products: " + e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
    }
  }

  @GetMapping("/{id}")
  public ResponseEntity<?> getProductById(@PathVariable Long id) {
    try {
      Optional<Product> product = productService.getProductById(id);
      if (product.isPresent()) {
        return new ResponseEntity<>(product.get(), HttpStatus.OK);
      } else {
        return new ResponseEntity<>("Product with ID " + id + " not found.", HttpStatus.NOT_FOUND);
      }
    } catch (Exception e) {
      return new ResponseEntity<>(
          "Error fetching product: " + e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
    }
  }

  @DeleteMapping("/{id}")
  public ResponseEntity<?> deleteProduct(@PathVariable Long id) {
    try {
      productService.deleteProduct(id);
      return new ResponseEntity<>("Product deleted successfully.", HttpStatus.NO_CONTENT);
    } catch (RuntimeException e) {
      return new ResponseEntity<>("Product with ID " + id + " not found.", HttpStatus.NOT_FOUND);
    } catch (Exception e) {
      return new ResponseEntity<>(
          "Error deleting product: " + e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
    }
  }
}
