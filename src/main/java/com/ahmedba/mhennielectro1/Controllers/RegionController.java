package com.ahmedba.mhennielectro1.Controllers;

import com.ahmedba.mhennielectro1.Entities.Pays;
import com.ahmedba.mhennielectro1.Entities.Region;
import com.ahmedba.mhennielectro1.Repositories.RegionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/region")
public class RegionController {


    @Autowired
    private RegionRepository regionRepository;


    @PostMapping
    public ResponseEntity<?> save(@RequestBody Region region) {
        if(regionRepository.findByLabel(region.getLabel()).isPresent()) {
            return ResponseEntity.badRequest().body(Map.of(
                    "message",
                    "region existe déja"
            ));
        }else{
            Region newRegion = regionRepository.save(region);
            return ResponseEntity.ok(newRegion);
        }
    }


    @GetMapping
    public ResponseEntity<?> findAll() {
        return ResponseEntity.ok(regionRepository.findAll());
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> update(@PathVariable Integer id, @RequestBody Region region) {
        if (regionRepository.existsById(id)) {

            region.setId(id); // 🔥 OBLIGATOIRE
            Region updatedRegion = regionRepository.save(region);

            return ResponseEntity.ok(updatedRegion);
        }

        return ResponseEntity.badRequest().body(
                Map.of("message", "region not found !")
        );
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable Integer id) {
        if(regionRepository.findById(id).isPresent()) {
            regionRepository.deleteById(id);
            return ResponseEntity.ok().build();
        }else{
            return ResponseEntity.badRequest().body(Map.of(
                    "message",
                    "region not found !"
            ));
        }
    }
}
