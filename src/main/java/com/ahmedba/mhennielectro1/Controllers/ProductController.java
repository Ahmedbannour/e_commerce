package com.ahmedba.mhennielectro1.Controllers;


import com.ahmedba.mhennielectro1.Entities.Categorie;
import com.ahmedba.mhennielectro1.Entities.Product;
import com.ahmedba.mhennielectro1.Repositories.ProductRepository;
import com.ahmedba.mhennielectro1.Utils.ApiResponse;
import com.ahmedba.mhennielectro1.Utils.StorageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/produits")
public class ProductController {

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private StorageService storageService; // On injecte le service ici

    @GetMapping("/getProdcutsByCategory/{categoryId}")
    public ResponseEntity<ApiResponse<List<Product>>> getProductsByCategory(@PathVariable Long categoryId) {
        List<Product> productsFiltred = productRepository.findByCategorieId(categoryId);
        return ResponseEntity.ok(new ApiResponse<>("success", "Liste des produits", productsFiltred));
    }

    @GetMapping("/{productId}")

    public Product getProductById(@PathVariable Long productId) {
        if(productRepository.findById(productId).isPresent()){
            return productRepository.findById(productId).get();
        }else{
            return null;
        }
    }




    @PostMapping(consumes = { MediaType.MULTIPART_FORM_DATA_VALUE })
    public ResponseEntity<ApiResponse<Product>> save(
            @RequestPart("product") Product product,
            @RequestPart(value = "image", required = false) MultipartFile image) {

        if (productRepository.findByRef(product.getRef()).isPresent()) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body(new ApiResponse<>("error", "Référence existante", null));
        }

        if (image != null && !image.isEmpty()) {
            // Utilisation du service pour stocker le fichier
            String fileName = storageService.store(image);
            product.setImage(fileName);
        }

        Product newProduct = productRepository.save(product);
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(new ApiResponse<>("success", "Produit créé", newProduct));
    }


    @GetMapping
    public ResponseEntity<List<Product>> getAll(){
        return ResponseEntity.ok().body(productRepository.findAll());
    }


    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable Long id){
        if(productRepository.findById(id).isPresent()){
            productRepository.deleteById(id);
            return ResponseEntity.ok(Map.of(
                    "message",
                    "product deleted successfully !"
            ));
        }else{
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(Map.of(
                    "message",
                    "product not found !"
            ));
        }
    }


    @PutMapping("/{id}")
    public ResponseEntity<?> update(@PathVariable Long id, @RequestBody Product product) {

        return productRepository.findById(id)
                .map(existing -> {
                    existing.setDescription(product.getDescription());
                    existing.setPrice(product.getPrice());
                    existing.setImage(product.getImage());
                    existing.setRef(product.getRef());
                    existing.setDate_achat(product.getDate_achat());

                    Product updated = productRepository.save(existing);
                    return ResponseEntity.ok(updated);
                })
                .orElse(ResponseEntity.notFound().build());
    }

}
