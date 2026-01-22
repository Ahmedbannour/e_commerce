package com.ahmedba.mhennielectro1.Controllers;

import com.ahmedba.mhennielectro1.Entities.Region;
import com.ahmedba.mhennielectro1.Entities.Ville;
import com.ahmedba.mhennielectro1.Repositories.RegionRepository;
import com.ahmedba.mhennielectro1.Repositories.VilleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/ville")
public class VilleController {


    @Autowired
    private VilleRepository villeRepository;



    @PostMapping
    public ResponseEntity<?> save(@RequestBody Ville ville) {
        if (villeRepository.findByLabel(ville.getLabel()).isPresent()) {
            return ResponseEntity.badRequest().body(Map.of("message", "ville existe déjà"));
        }

        Ville saved = villeRepository.save(ville);
        return ResponseEntity.ok(saved);
    }



    @GetMapping
    public ResponseEntity<?> findAll() {
        return ResponseEntity.ok(villeRepository.findAll());
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> update(@PathVariable Integer id, @RequestBody Ville ville) {
        if (villeRepository.existsById(id)) {

            ville.setId(id); // 🔥 OBLIGATOIRE
            Ville updatedVille = villeRepository.save(ville);

            return ResponseEntity.ok(updatedVille);
        }

        return ResponseEntity.badRequest().body(
                Map.of("message", "region not found !")
        );
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable Integer id) {
        if(villeRepository.findById(id).isPresent()) {
            villeRepository.deleteById(id);
            return ResponseEntity.ok().build();
        }else{
            return ResponseEntity.badRequest().body(Map.of(
                    "message",
                    "pays not found !"
            ));
        }
    }
}
