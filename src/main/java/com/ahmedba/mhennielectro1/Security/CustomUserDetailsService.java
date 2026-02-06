package com.ahmedba.mhennielectro1.Security;

import com.ahmedba.mhennielectro1.Entities.Users;
import com.ahmedba.mhennielectro1.Repositories.UserRepository;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class CustomUserDetailsService implements UserDetailsService {

    private final UserRepository userRepository;

    public CustomUserDetailsService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    /**
     * Spring Security appelle automatiquement cette méthode
     * lors du login.
     *
     * 👉 Dans notre cas : username = email
     */
    @Override
    public UserDetails loadUserByUsername(String email)
            throws UsernameNotFoundException {

        Users users = userRepository.findByEmail(email)
                .orElseThrow(() ->
                        new UsernameNotFoundException(
                                "User not found with email: " + email
                        )
                );

        return org.springframework.security.core.userdetails.User
                .withUsername(users.getEmail())                // login via email
                .password(users.getPassword())                 // mot de passe hashé
                .authorities("ROLE_" + users.getRole().getName()) // ROLE_ADMIN / ROLE_CLIENT
                .disabled(!users.isActive())                   // compte actif ?
                .build();
    }
}
