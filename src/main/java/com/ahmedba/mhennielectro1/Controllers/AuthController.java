package com.ahmedba.mhennielectro1.Controllers;

import com.ahmedba.mhennielectro1.DTO.*;
import com.ahmedba.mhennielectro1.Entities.Role;
import com.ahmedba.mhennielectro1.Entities.Users;
import com.ahmedba.mhennielectro1.Repositories.RoleRepository;
import com.ahmedba.mhennielectro1.Repositories.UserRepository;
import com.ahmedba.mhennielectro1.Security.JwtService;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.Date;

@RestController
@RequestMapping("/api/auth")
public class AuthController {

    private final UserRepository userRepository;
    private final RoleRepository roleRepository;
    private final PasswordEncoder passwordEncoder;
    private final AuthenticationManager authenticationManager;
    private final JwtService jwtService;

    public AuthController(UserRepository userRepository,
                          RoleRepository roleRepository,
                          PasswordEncoder passwordEncoder,
                          AuthenticationManager authenticationManager,
                          JwtService jwtService) {
        this.userRepository = userRepository;
        this.roleRepository = roleRepository;
        this.passwordEncoder = passwordEncoder;
        this.authenticationManager = authenticationManager;
        this.jwtService = jwtService;
    }

    // ✅ REGISTER (formulaire complet)
    @PostMapping("/register")
    public ResponseEntity<?> register(@RequestBody RegisterRequestDTO req) {

        if (userRepository.existsByEmail(req.getEmail())) {
            return ResponseEntity.badRequest().body("Email already exists");
        }

        if (req.getNom() == null || req.getNom().isBlank()
                || req.getPrenom() == null || req.getPrenom().isBlank()
                || req.getPassword() == null || req.getPassword().length() < 6
                || req.getPhone() == null || req.getPhone().isBlank()
                || req.getDateNaissance() == null) {
            return ResponseEntity.badRequest().body("Missing or invalid fields");
        }

        String roleName = (req.getRole() == null || req.getRole().isBlank())
                ? "CLIENT"
                : req.getRole().trim().toUpperCase();

        Role role = roleRepository.findByName(roleName)
                .orElseGet(() -> roleRepository.save(new Role(roleName)));

        Users users = new Users();
        users.setNom(req.getNom());
        users.setPrenom(req.getPrenom());
        users.setEmail(req.getEmail());
        users.setPhone(req.getPhone());
        users.setDateNaissance(req.getDateNaissance());
        users.setPassword(passwordEncoder.encode(req.getPassword()));

        users.setRole(role);

        users.setActive(true);
        users.setVerified(false);
        users.setCreatedAt(new Date());

        userRepository.save(users);

        return ResponseEntity.ok(
                new RegisterResponseDTO(
                        users.getId(),
                        users.getNom(),
                        users.getPrenom(),
                        users.getEmail(),
                        users.getRole().getName(),
                        null,
                        "User registered successfully"
                )
        );
    }

    // ✅ LOGIN (email + password)
    @PostMapping("/login")
    public ResponseEntity<LoginResponseDTO> login(@RequestBody LoginRequestDTO req) {

        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(req.getEmail(), req.getPassword())
        );

        Users users = userRepository.findByEmail(req.getEmail()).orElseThrow();
        String token = jwtService.generateToken(users.getEmail());

        return ResponseEntity.ok(new LoginResponseDTO(
                token,
                users.getId(),
                users.getNom(),
                users.getPrenom(),
                users.getEmail(),
                users.getRole().getName()
        ));
    }
}
