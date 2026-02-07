package com.ahmedba.mhennielectro1.Repositories;

import com.ahmedba.mhennielectro1.Entities.Categorie;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface CategorieRepository extends JpaRepository<Categorie, Long> {
    // Pour afficher le menu principal par exemple
    List<Categorie> findByParentIsNull();
}