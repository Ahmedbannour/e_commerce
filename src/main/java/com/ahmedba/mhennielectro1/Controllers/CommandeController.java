package com.ahmedba.mhennielectro1.Controllers;

import com.ahmedba.mhennielectro1.Entities.Commande;
import com.ahmedba.mhennielectro1.Entities.Livreur;
import com.ahmedba.mhennielectro1.Entities.User;
import com.ahmedba.mhennielectro1.Repositories.CommandeRepository;
import com.ahmedba.mhennielectro1.Repositories.UserRepository;
import com.ahmedba.mhennielectro1.Services.CommandeRequest;
import com.ahmedba.mhennielectro1.Services.CommandeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/commandes")
public class CommandeController {


    @Autowired
    private CommandeRepository commandeRepository;

    @Autowired
    private CommandeService commandeService;
    @Autowired
    private UserRepository userRepository;

    @GetMapping
    public ResponseEntity<List<Commande>> findAll(){
        return new ResponseEntity<>(commandeRepository.findAll(), HttpStatus.OK);
    }


    @PostMapping
    public ResponseEntity<?> ajouterCommande(@RequestBody CommandeRequest request, Principal principal) {
        if (principal == null) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED)
                    .body(Map.of("error", "Utilisateur non authentifié (Token invalide ou absent)"));
        }

        try {
            String email = principal.getName();
            User user = userRepository.findByEmail(email)
                    .orElseThrow(() -> new RuntimeException("Utilisateur non trouvé en base"));

            Commande nouvelleCommande = commandeService.saveCommande(request, user);
            return new ResponseEntity<>(nouvelleCommande, HttpStatus.CREATED);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(Map.of("error", e.getMessage()));
        }
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
