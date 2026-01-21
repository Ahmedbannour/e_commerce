package com.ahmedba.mhennielectro1.Controllers;

import com.ahmedba.mhennielectro1.Entities.Commande;
import com.ahmedba.mhennielectro1.Entities.Livreur;
import com.ahmedba.mhennielectro1.Entities.Product;
import com.ahmedba.mhennielectro1.Repositories.LivreurRipository;
import com.ahmedba.mhennielectro1.Repositories.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/livreur")
public class LivreurController {

    @Autowired
    private LivreurRipository livreurRipository;


    @GetMapping
    public ResponseEntity<?> findAll(){
        return ResponseEntity.ok(livreurRipository.findAll());
    }


    @PostMapping
    public ResponseEntity<?> save(@RequestBody Livreur livreur){
        if(livreurRipository.findByEmail(livreur.getEmail()).isPresent()){
            return ResponseEntity.badRequest().body(Map.of(
                    "message",
                    "livreur existe déja !"
                )
            );
        }else{
             Livreur newLivreur = livreurRipository.save(livreur);
             return ResponseEntity.ok(newLivreur);
        }
    }


    @PutMapping("/{id}")
    public ResponseEntity<?> update(@PathVariable Long id, @RequestBody Livreur livreur) {

        return livreurRipository.findById(id)
                .map(existing -> {

                    existing.setNom(livreur.getNom());
                    existing.setPrenom(livreur.getPrenom());
                    existing.setPhone(livreur.getPhone());
                    existing.setPassword(livreur.getPassword());
                    existing.setId_ville(livreur.getId_ville());
                    existing.setDate_naissance(livreur.getDate_naissance());
                    existing.setSocieteLivraison(livreur.getSocieteLivraison());

                    // ✅ GESTION CORRECTE DES COMMANDES
                    if (livreur.getCommandes() != null) {
                        existing.getCommandes().clear();

                        for (Commande c : livreur.getCommandes()) {
                            c.setLivreur(existing); // 🔴 OBLIGATOIRE
                            existing.getCommandes().add(c);
                        }
                    }

                    Livreur updated = livreurRipository.save(existing);
                    return ResponseEntity.ok(updated);
                })
                .orElseGet(() -> ResponseEntity.badRequest().body(
                        (Livreur) Map.of("message", "livreur n existe pas !")
                ));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable Long id){
        if(livreurRipository.findById(id).isPresent()){
            livreurRipository.deleteById(id);

            return ResponseEntity.ok(Map.of(
                    "message",
                    "Livreur deleted successfully !"
            ));
        }else{
            return ResponseEntity.badRequest().body(Map.of(
                    "message",
                    "livreur n existe pas !"
            ));
        }
    }
}
