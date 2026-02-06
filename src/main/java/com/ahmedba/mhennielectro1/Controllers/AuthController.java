package com.ahmedba.mhennielectro1.Controllers;

import com.ahmedba.mhennielectro1.DTO.*;
import com.ahmedba.mhennielectro1.Entities.RefreshToken;
import com.ahmedba.mhennielectro1.Entities.Role;
import com.ahmedba.mhennielectro1.Entities.Users;
import com.ahmedba.mhennielectro1.Repositories.RefreshTokenRepository;
import com.ahmedba.mhennielectro1.Repositories.RoleRepository;
import com.ahmedba.mhennielectro1.Repositories.UserRepository;
import com.ahmedba.mhennielectro1.Security.JwtService;
import com.ahmedba.mhennielectro1.Security.RefreshTokenService;
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
    private final RefreshTokenRepository refreshTokenRepository;
    private final RefreshTokenService refreshTokenService;
    private final PasswordEncoder passwordEncoder;
    private final AuthenticationManager authenticationManager;
    private final JwtService jwtService;

    // ✅ Injection par constructeur (BEST PRACTICE)
    public AuthController(
            UserRepository userRepository,
            RoleRepository roleRepository,
            RefreshTokenRepository refreshTokenRepository,
            RefreshTokenService refreshTokenService,
            PasswordEncoder passwordEncoder,
            AuthenticationManager authenticationManager,
            JwtService jwtService
    ) {
        this.userRepository = userRepository;
        this.roleRepository = roleRepository;
        this.refreshTokenRepository = refreshTokenRepository;
        this.refreshTokenService = refreshTokenService;
        this.passwordEncoder = passwordEncoder;
        this.authenticationManager = authenticationManager;
        this.jwtService = jwtService;
    }

    // ================= REGISTER =================

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
                        "User registered successfully"
                )
        );
    }

    // ================= LOGIN =================

    @PostMapping("/login")
    public ResponseEntity<LoginResponseDTO> login(@RequestBody LoginRequestDTO req) {

        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(req.getEmail(), req.getPassword())
        );

        Users user = userRepository.findByEmail(req.getEmail())
                .orElseThrow(() -> new RuntimeException("User not found"));

        String accessToken = jwtService.generateToken(user.getEmail());

        RefreshToken refreshToken = refreshTokenService.createRefreshToken(user.getId());

        return ResponseEntity.ok(
                new LoginResponseDTO(
                        accessToken,
                        refreshToken.getToken(),
                        user.getId(),
                        user.getNom(),
                        user.getPrenom(),
                        user.getEmail(),
                        user.getRole().getName()
                )
        );
    }

    // ================= REFRESH TOKEN =================

    @PostMapping("/refresh")
    public ResponseEntity<RefreshTokenResponse> refreshToken(
            @RequestBody RefreshTokenRequest request
    ) {
        RefreshToken refreshToken = refreshTokenRepository
                .findByToken(request.getRefreshToken())
                .map(refreshTokenService::verifyExpiration)
                .orElseThrow(() -> new RuntimeException("Invalid refresh token"));

        Users user = refreshToken.getUsers();

        String newAccessToken = jwtService.generateToken(user.getEmail());

        return ResponseEntity.ok(
                new RefreshTokenResponse(
                        newAccessToken,
                        refreshToken.getToken()
                )
        );
    }

    @PostMapping("/logout")
    public ResponseEntity<?> logout(@RequestBody LogoutRequestDTO request) {

        Users user = userRepository.findByEmail(request.getEmail())
                .orElseThrow(() -> new RuntimeException("User not found"));

        refreshTokenService.deleteByUser(user);

        return ResponseEntity.ok(
                new LogoutResponseDTO("Logout successful")
        );
    }

}
