package com.example.backend.Service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.backend.Model.Brand;
import com.example.backend.Repository.BrandRepository;

@Service
public class BrandService {

  @Autowired private BrandRepository brandRepository;

  public List<Brand> getAllBrands() {
    return brandRepository.findAll();
  }

  public Optional<Brand> getBrandById(Long id) {
    return brandRepository.findById(id);
  }

  public Brand saveBrand(Brand brand) {
    return brandRepository.save(brand);
  }

  public boolean deleteBrandById(Long id) {
    if (brandRepository.existsById(id)) {
      brandRepository.deleteById(id);
      return true;
    } else {
      return false;
    }
  }
}
