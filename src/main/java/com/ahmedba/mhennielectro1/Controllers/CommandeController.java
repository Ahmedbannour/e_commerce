package com.ahmedba.mhennielectro1.Controllers;

import com.ahmedba.mhennielectro1.Entities.Commande;
import com.ahmedba.mhennielectro1.Entities.Livreur;
import com.ahmedba.mhennielectro1.Repositories.CommandeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/commandes")
public class CommandeController {


    @Autowired
    private CommandeRepository commandeRepository;


    @GetMapping
    public ResponseEntity<List<Commande>> findAll(){
        return new ResponseEntity<>(commandeRepository.findAll(), HttpStatus.OK);
    }


    @PostMapping
    public ResponseEntity<Commande> ajouterCommande(@RequestBody Commande commande) {
        return  new ResponseEntity<>(commandeRepository.save(commande), HttpStatus.OK);
    }


    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteCommande(@PathVariable long id, @RequestBody Commande commande) {
        if(commandeRepository.findById(id).isPresent()) {
            commandeRepository.deleteById(id);
            return new ResponseEntity<>(commande, HttpStatus.OK);
        }else{
            return ResponseEntity.badRequest().body(Map.of(
                    "message",
                    "commande not found !"
            ));
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<Commande> updateCommande(@PathVariable long id, @RequestBody Commande commande) {
        return commandeRepository.findById(id).map(
                        existing -> {

                    existing.setDate_commande(commande.getDate_commande());
                    existing.setLigneCommandes(commande.getLigneCommandes());
                    existing.setUser(commande.getUser());
                    existing.setLivreur(commande.getLivreur());

                    Commande updated = commandeRepository.save(existing);
                    return ResponseEntity.ok(updated);
                })
                .orElseGet(() -> ResponseEntity.badRequest().body(
                        (Commande) Map.of("message", "commande n existe pas !")
                ));
    }

}
