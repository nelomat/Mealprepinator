package com.example.backend.Service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.backend.Model.Unit;
import com.example.backend.Repository.UnitRepository;

@Service
public class UnitService {

  @Autowired private UnitRepository unitRepository;

  public List<Unit> getAllUnits() {
    return unitRepository.findAll();
  }

  public Optional<Unit> getUnitById(Long id) {
    return unitRepository.findById(id);
  }

  public Unit saveUnit(Unit unit) {
    return unitRepository.save(unit);
  }

  public boolean deleteUnitById(Long id) {
    if (unitRepository.existsById(id)) {
      unitRepository.deleteById(id);
      return true;
    } else {
      return false;
    }
  }
}
