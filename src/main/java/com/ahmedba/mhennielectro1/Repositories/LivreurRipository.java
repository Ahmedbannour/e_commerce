package com.ahmedba.mhennielectro1.Repositories;

import com.ahmedba.mhennielectro1.Entities.Livreur;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface LivreurRipository extends JpaRepository<Livreur, Long> {
    Optional<Livreur> findByEmail(String email);
}
