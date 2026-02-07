package com.ahmedba.mhennielectro1.Services;

import com.ahmedba.mhennielectro1.Entities.Role;
import com.ahmedba.mhennielectro1.Entities.User;
import com.ahmedba.mhennielectro1.Repositories.RoleRepository;
import com.ahmedba.mhennielectro1.Repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private RoleRepository roleRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    public User registerUser(User user) {
        // 1. Vérifier si l'email existe
        if (userRepository.findByEmail(user.getEmail()).isPresent()) {
            throw new RuntimeException("Cet email est déjà utilisé !");
        }

        // 2. Hachage du mot de passe (CRUCIAL pour la sécurité)
        user.setPassword(passwordEncoder.encode(user.getPassword()));

        // 3. Attribution du rôle par défaut (ID = 1)
        Role defaultRole = roleRepository.findById(1L).orElseThrow(() -> new RuntimeException("Erreur : Rôle par défaut non trouvé en base."));
        user.setRole(defaultRole);

        return userRepository.save(user);
    }
}