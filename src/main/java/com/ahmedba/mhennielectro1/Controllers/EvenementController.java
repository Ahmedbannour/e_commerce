package com.ahmedba.mhennielectro1.Controllers;


import com.ahmedba.mhennielectro1.Entities.Evenement;
import com.ahmedba.mhennielectro1.Entities.Product;
import com.ahmedba.mhennielectro1.Entities.Role;
import com.ahmedba.mhennielectro1.Repositories.DepotRepository;
import com.ahmedba.mhennielectro1.Repositories.EvenementRepository;
import com.ahmedba.mhennielectro1.Utils.ApiResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/evenements")
public class EvenementController {

    @Autowired
    private EvenementRepository evenementsRepository;

    @GetMapping
    public ResponseEntity<ApiResponse<List<Evenement>>> findAll() {
        List<Evenement> evenments = evenementsRepository.findAll();
        return ResponseEntity.ok(new ApiResponse<>("success", "Liste des produits", evenments));
    }


    @PostMapping
    public ResponseEntity<ApiResponse<Evenement>> save(@RequestBody Evenement evenement) {
        Evenement newEvent = evenementsRepository.save(evenement);
        return ResponseEntity.ok(new ApiResponse<>("success", "Evenement", newEvent));
    }


    @PutMapping("/{id}")
    public ResponseEntity<ApiResponse<Evenement>> update(@PathVariable Long id, @RequestBody Evenement evenement) {
        return evenementsRepository.findById(id).map(
                existing ->{
                    existing.setId(id);
                    existing.setLabel(evenement.getLabel());
                    existing.setDate_debut(evenement.getDate_debut());
                    existing.setDate_fin(evenement.getDate_fin());
                    evenementsRepository.save(existing);

                    return ResponseEntity.ok(new ApiResponse<>("success", "Evenement", existing));

                }).orElse(ResponseEntity.ok(new ApiResponse<>("error", "Evenement", null)));
    }


    @DeleteMapping("/{id}")
    public ResponseEntity<ApiResponse<Evenement>> delete(@PathVariable Long id) {
        if(evenementsRepository.existsById(id)){
            evenementsRepository.deleteById(id);
            return ResponseEntity.ok(new ApiResponse<>("success", "Evenement", null));
        }else {
            return ResponseEntity.ok(new ApiResponse<>("error", "Evenement", null));
        }
    }
}
