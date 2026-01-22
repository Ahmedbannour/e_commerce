package com.ahmedba.mhennielectro1.Repositories;

import com.ahmedba.mhennielectro1.Entities.Region;
import com.ahmedba.mhennielectro1.Entities.Ville;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface VilleRepository extends JpaRepository<Ville, Integer> {
    Optional<?> findByLabel(String label);
}
