package com.ahmedba.mhennielectro1.Controllers;


import com.ahmedba.mhennielectro1.Entities.Product;
import com.ahmedba.mhennielectro1.Repositories.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/produits")
public class ProductController {

    @Autowired
    private ProductRepository productRepository;


    @PostMapping
    public ResponseEntity<?> save(@RequestBody Product product){
        if(productRepository.findByRef(product.getRef()).isPresent()){
            return ResponseEntity.badRequest().body("❌ produit existe déja !");
        }else{
            Product newProduct = productRepository.save(product);
            return ResponseEntity.ok().body(newProduct);
        }
    }


    @GetMapping
    public ResponseEntity<List<Product>>  getAll(){
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
