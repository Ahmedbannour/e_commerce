package com.ahmedba.mhennielectro1.Controllers;

import com.ahmedba.mhennielectro1.Entities.Pays;
import com.ahmedba.mhennielectro1.Repositories.PaysRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/pays")
public class PaysController {

    @Autowired
    private PaysRepository paysRepository;


    @PostMapping
    public ResponseEntity<?> save(@RequestBody Pays pays) {
        if(paysRepository.findByLabel(pays.getLabel()).isPresent()) {
            return ResponseEntity.badRequest().body(Map.of(
                    "message",
                    "pays existe déja"
            ));
        }else{
            Pays newPays = paysRepository.save(pays);
            return ResponseEntity.ok(newPays);
        }
    }


    @GetMapping
    public ResponseEntity<?> findAll() {
        return ResponseEntity.ok(paysRepository.findAll());
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> update(@PathVariable Integer id, @RequestBody Pays pays) {

        if (paysRepository.existsById(id)) {

            pays.setId(id); // 🔥 OBLIGATOIRE
            Pays updatedPays = paysRepository.save(pays);

            return ResponseEntity.ok(updatedPays);
        }

        return ResponseEntity.badRequest().body(
                Map.of("message", "pays not found !")
        );
    }


    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable Integer id) {
        if(paysRepository.findById(id).isPresent()) {
            paysRepository.deleteById(id);
            return ResponseEntity.ok().build();
        }else{
            return ResponseEntity.badRequest().body(Map.of(
                    "message",
                    "pays not found !"
            ));
        }
    }
}
