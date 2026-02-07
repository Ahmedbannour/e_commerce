package com.ahmedba.mhennielectro1.Controllers;

import com.ahmedba.mhennielectro1.Entities.User;
import com.ahmedba.mhennielectro1.Repositories.UserRepository;
import com.ahmedba.mhennielectro1.Services.UserService;
import com.ahmedba.mhennielectro1.Utils.ApiResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private UserService userService;

    @GetMapping
    public List<User> findAll() {
        return userRepository.findAll();
    }





    @PostMapping
    public ResponseEntity<?> save(@RequestBody User user) {
        if(userRepository.findByEmail(user.getEmail()).isPresent()){
            return ResponseEntity.badRequest().body("❌ user existe déja !");
        }else{
            User newUser = userRepository.save(user);
            return new ResponseEntity<>(newUser, HttpStatus.CREATED);
        }
    }

    @PostMapping("/register")
    public ResponseEntity<ApiResponse<?>> register(@RequestBody User user) {
        try {
            User newUser = userService.registerUser(user);
            return ResponseEntity.status(HttpStatus.CREATED).body(new ApiResponse<>("success", "Utilisateur créé avec succès", newUser));
        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new ApiResponse<>("error", e.getMessage(), null));
        }
    }


    @GetMapping("/test")
    public String userAccess() {
        return "✅ USER ACCESS GRANTED";
    }

}
