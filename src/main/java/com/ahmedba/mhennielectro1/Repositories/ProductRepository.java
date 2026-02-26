package com.ahmedba.mhennielectro1.Repositories;

import com.ahmedba.mhennielectro1.Entities.Product;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface ProductRepository extends JpaRepository<Product, Long> {
    Optional<Product> findByRef(String ref);

    List<Product> findByCategorieId(Long categorieId);

    // Optionnel : Récupère les produits d'une catégorie ET de ses sous-catégories
    List<Product> findByCategorieIdOrCategorieParentId(Long catId, Long parentId);
}