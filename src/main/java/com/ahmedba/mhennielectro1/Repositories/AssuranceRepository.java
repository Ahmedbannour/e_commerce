package com.ahmedba.mhennielectro1.Repositories;

import com.ahmedba.mhennielectro1.Entities.Assurance;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface AssuranceRepository extends JpaRepository<Assurance, Long> {
    Optional<?> findByMatricule(String matricule);
}
