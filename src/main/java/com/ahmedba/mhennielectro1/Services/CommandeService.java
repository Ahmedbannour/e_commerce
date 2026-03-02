package com.ahmedba.mhennielectro1.Services;

import com.ahmedba.mhennielectro1.Entities.*;
import com.ahmedba.mhennielectro1.Repositories.CommandeRepository;
import com.ahmedba.mhennielectro1.Repositories.ProductRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Service
public class CommandeService {


    @Autowired private CommandeRepository commandeRepository;
    @Autowired private ProductRepository productRepository;

    @Transactional
    public Commande saveCommande(CommandeRequest request, User user) {
        Commande commande = new Commande();

        // Simulation d'utilisateur si user est null pour vos tests Postman
        if (user != null) {
            commande.setUser(user);
        }

        commande.setDate_commande(new java.util.Date());
        List<LigneCommande> lignes = new ArrayList<>();
        double montantTotal = 0;

        for (CartItemDTO item : request.getItems()) {
            Product product = productRepository.findById(item.getProductId())
                    .orElseThrow(() -> new RuntimeException("Produit ID " + item.getProductId() + " introuvable"));

            // Vérification du stock total (calculé sur les dépôts)
            if (product.getTotalStock() < item.getQuantite()) {
                throw new RuntimeException("Stock insuffisant pour : " + product.getLabel());
            }

            // Logique de Déstockage
            double resteAPrelever = (double) item.getQuantite();
            for (DepotProduct dp : product.getDepotProducts()) {
                if (resteAPrelever <= 0) break;
                double stockInitial = dp.getQuantity();
                if (stockInitial > 0) {
                    double prelevement = Math.min(stockInitial, resteAPrelever);
                    dp.setQuantity(stockInitial - prelevement);
                    resteAPrelever -= prelevement;
                }
            }

            // Création de la Ligne de Commande (Traçabilité)
            LigneCommande lc = LigneCommande.builder()
                    .product(product)
                    .quantite(item.getQuantite())
                    .prixUnitaire(product.getPrice()) // ON FIGE LE PRIX ICI
                    .commande(commande)
                    .build();

            lignes.add(lc);
            montantTotal += (lc.getPrixUnitaire() * item.getQuantite());
        }

        commande.setLigneCommandes(lignes);
        commande.setTotal(montantTotal);

        return commandeRepository.save(commande);
    }


}