package com.ahmedba.mhennielectro1.Controllers;


import com.ahmedba.mhennielectro1.Entities.Role;
import com.ahmedba.mhennielectro1.Repositories.RoleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/role")
public class RoleController {

    @Autowired
    private RoleRepository roleRepository;


    @GetMapping
    public List<Role> findAll() {
        return roleRepository.findAll();
    }

    @PostMapping
    public Role save(@RequestBody Role role) {
        if(roleRepository.findByName(role.getName()).isPresent())
            return roleRepository.findByName(role.getName()).get();
        else
            return roleRepository.save(role);
    }


    @PutMapping("/{id}")
    public ResponseEntity<?> update(@PathVariable Long id ,@RequestBody Role role) {
        return roleRepository.findById(id).map(
                existing ->{
                    existing.setName(role.getName());

                    Role updated = roleRepository.save(existing);
                    return ResponseEntity.ok(updated);
                }).orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable Long id) {
        if(roleRepository.findById(id).isPresent()){
            roleRepository.deleteById(id);
            return ResponseEntity.ok().build();
        }else{
            return ResponseEntity.ok().build();
        }
    }

}
