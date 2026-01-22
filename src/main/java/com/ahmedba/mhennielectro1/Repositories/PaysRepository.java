package com.ahmedba.mhennielectro1.Repositories;

import com.ahmedba.mhennielectro1.Entities.Pays;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface PaysRepository extends JpaRepository<Pays, Integer> {

    Optional<Pays> findByLabel(String label);
}
