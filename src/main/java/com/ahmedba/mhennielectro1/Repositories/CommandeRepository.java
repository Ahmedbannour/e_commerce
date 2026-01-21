package com.ahmedba.mhennielectro1.Repositories;

import com.ahmedba.mhennielectro1.Entities.Commande;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.expression.spel.ast.Operator;

import java.util.Optional;

public interface CommandeRepository extends JpaRepository<Commande, Long> {

}

