package com.example.backend.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.backend.Model.Unit;

@Repository
public interface UnitRepository extends JpaRepository<Unit, Long> {}
