package com.ahmedba.mhennielectro1.Controllers;

import com.ahmedba.mhennielectro1.Entities.Assurance;
import com.ahmedba.mhennielectro1.Entities.Product;
import com.ahmedba.mhennielectro1.Repositories.AssuranceRepository;
import com.ahmedba.mhennielectro1.Repositories.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/assurance")
public class AssuranceController {

    @Autowired
    private AssuranceRepository assuranceRepository;


    @PostMapping
    public ResponseEntity<?> save(@RequestBody Assurance assurance) {
        if(assuranceRepository.findByMatricule(assurance.getMatricule()).isPresent()){
            return ResponseEntity.badRequest().body(Map.of(
                    "message" , "assurance existe déja"
            ));
        }else{
            Assurance newAssurance = assuranceRepository.save(assurance);
            return ResponseEntity.ok(newAssurance);
        }
    }


    @GetMapping
    public List<Assurance> findAll() {
        return assuranceRepository.findAll();
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> update(@PathVariable Long id, @RequestBody Assurance assurance) {
        return assuranceRepository.findById(id)
                .map(existing -> {
                    existing.setMatricule(assurance.getMatricule());
                    existing.setLabel(assurance.getLabel());

                    Assurance updated = assuranceRepository.save(existing);
                    return ResponseEntity.ok(updated);
                })
                .orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable Long id) {
        if(assuranceRepository.findById(id).isPresent()){
            assuranceRepository.deleteById(id);
            return ResponseEntity.ok(Map.of(
                    "message",
                    "Assurance deleted successfully !"
            ));
        }else{
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(Map.of(
                    "message",
                    "Assurance not found !"
            ));
        }
    }
}
