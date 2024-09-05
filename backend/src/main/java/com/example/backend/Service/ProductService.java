package com.example.backend.Service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.backend.Model.Brand;
import com.example.backend.Model.Product;
import com.example.backend.Model.Unit;
import com.example.backend.Repository.ProductRepository;

@Service
public class ProductService {

  private final ProductRepository productRepository;

  private final BrandService brandService;
  private final UnitService unitService;

  @Autowired
  public ProductService(
      ProductRepository productRepository, BrandService brandService, UnitService unitService) {
    this.productRepository = productRepository;
    this.brandService = brandService;
    this.unitService = unitService;
  }

  public Product saveProduct(Product product) {
    createBrandIfNotExists(product);
    createUnitIfNotExists(product);
    return productRepository.save(product);
  }

  public boolean checkIfProductAlreadyExists(Product product) {
    String brandName = product.getBrand() != null ? product.getBrand().getName() : "";

    Optional<Product> existingProduct =
        productRepository.findByNameAndBrandName(product.getName(), brandName);
    return existingProduct.isPresent();
  }

  public List<Product> getAllProducts() {
    return productRepository.findAll();
  }

  public Optional<Product> getProductById(Long id) {
    return productRepository.findById(id);
  }

  public void deleteProduct(Long id) {
    if (productRepository.existsById(id)) {
      productRepository.deleteById(id);
    } else {
      throw new RuntimeException("Product not found with ID: " + id);
    }
  }

  private void createBrandIfNotExists(Product product) {
    List<Brand> existingBrands = brandService.getAllBrands();
    Brand incomingBrand = product.getBrand();

    boolean isBrandExisting =
        existingBrands.stream()
            .anyMatch(
                existingBrand -> existingBrand.getName().equalsIgnoreCase(incomingBrand.getName()));

    Brand managedBrand;

    if (!isBrandExisting) {
      managedBrand = brandService.saveBrand(incomingBrand);
    } else {
      managedBrand =
          existingBrands.stream()
              .filter(
                  existingBrand ->
                      existingBrand.getName().equalsIgnoreCase(incomingBrand.getName()))
              .findFirst()
              .orElseThrow(() -> new RuntimeException("Brand not found"));
    }
    product.setBrand(managedBrand);
  }

  private void createUnitIfNotExists(Product product) {
    List<Unit> existingUnits = unitService.getAllUnits();
    Unit incomingUnit = product.getUnit();

    boolean isUnitExisting =
        existingUnits.stream()
            .anyMatch(
                existingUnit -> existingUnit.getName().equalsIgnoreCase(incomingUnit.getName()));

    Unit managedUnit;

    if (!isUnitExisting) {
      managedUnit = unitService.saveUnit(incomingUnit);
    } else {
      managedUnit =
          existingUnits.stream()
              .filter(
                  existingUnit -> existingUnit.getName().equalsIgnoreCase(incomingUnit.getName()))
              .findFirst()
              .orElseThrow(() -> new RuntimeException("Unit not found"));
    }
    product.setUnit(managedUnit);
  }
}
