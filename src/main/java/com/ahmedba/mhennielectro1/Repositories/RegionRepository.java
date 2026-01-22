package com.ahmedba.mhennielectro1.Repositories;

import com.ahmedba.mhennielectro1.Entities.Region;
import com.ahmedba.mhennielectro1.Entities.Ville;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface RegionRepository extends JpaRepository<Region, Integer> {
    Optional<Region> findByLabel(String label);
}
