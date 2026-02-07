package com.ahmedba.mhennielectro1.Controllers;

import com.ahmedba.mhennielectro1.Entities.Categorie;
import com.ahmedba.mhennielectro1.Repositories.CategorieRepository;
import com.ahmedba.mhennielectro1.Utils.ApiResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/categories")
public class CategorieController {

    @Autowired
    private CategorieRepository categorieRepository;

    @GetMapping("/roots")
    public ResponseEntity<ApiResponse<List<Categorie>>> getRootCategories() {
        List<Categorie> roots = categorieRepository.findByParentIsNull();
        return ResponseEntity.ok(new ApiResponse<>("success", "Liste des catégories racines", roots));
    }

    @PostMapping
    public ResponseEntity<ApiResponse<Categorie>> create(@RequestBody Categorie categorie) {
        Categorie saved = categorieRepository.save(categorie);
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(new ApiResponse<>("success", "Catégorie créée", saved));
    }
}