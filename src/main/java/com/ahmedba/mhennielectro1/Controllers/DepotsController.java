package com.ahmedba.mhennielectro1.Controllers;

import com.ahmedba.mhennielectro1.Entities.Depot;
import com.ahmedba.mhennielectro1.Entities.User;
import com.ahmedba.mhennielectro1.Entities.Ville;
import com.ahmedba.mhennielectro1.Repositories.DepotRepository;
import com.ahmedba.mhennielectro1.Repositories.VilleRepository;
import com.ahmedba.mhennielectro1.Utils.ApiResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/depots")
public class DepotsController {


    @Autowired
    private DepotRepository depotRepository;




    @PostMapping
    public ResponseEntity<ApiResponse<?>> save(@RequestBody Depot depot) {
        if (depotRepository.findByLabel(depot.getLabel()).isPresent()) {
            return ResponseEntity.status(HttpStatus.OK).body(new ApiResponse<>("error", "depot existe déja", depotRepository.findByLabel(depot.getLabel())));
        }else{
            Depot newDepot = depotRepository.save(depot);
            return ResponseEntity.status(HttpStatus.OK).body(new ApiResponse<>("success", null, newDepot));

        }
    }

    @GetMapping
    public ResponseEntity<ApiResponse<?>> getDepots() {
        return ResponseEntity.status(HttpStatus.OK).body(new ApiResponse<>("success", null, depotRepository.findAll()));
    }



    @PutMapping("/{id}")
    public ResponseEntity<ApiResponse<?>> update(@RequestBody Depot depot ,  @PathVariable Integer id) {
        if (depotRepository.existsById(Long.valueOf(id))) {
            depot.setId(Long.valueOf(id)); // 🔥 OBLIGATOIRE

            Depot updatedDepot = depotRepository.save(depot);

            return ResponseEntity.status(HttpStatus.OK).body(new ApiResponse<>("success", null, updatedDepot));
        }else{
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new ApiResponse<>("error", "depot not found", null));
        }
    }


    @DeleteMapping("/{id}")
    public ResponseEntity<ApiResponse<?>> delete(@PathVariable Integer id) {
        if (depotRepository.findById(Long.valueOf(id)).isPresent()) {
            depotRepository.deleteById(Long.valueOf(id));
            return ResponseEntity.status(HttpStatus.OK).body(new ApiResponse<>("success", "depot supprimer avec success", null));
        }

        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new ApiResponse<>("error", "depot not found", null));
    }
}
