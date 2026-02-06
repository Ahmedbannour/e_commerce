package com.ahmedba.mhennielectro1.Security;

import com.ahmedba.mhennielectro1.Entities.User;
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

        User user = userRepository.findByEmail(email)
                .orElseThrow(() ->
                        new UsernameNotFoundException(
                                "User not found with email: " + email
                        )
                );

        return org.springframework.security.core.userdetails.User
                .withUsername(user.getEmail())                // login via email
                .password(user.getPassword())                 // mot de passe hashé
                .authorities("ROLE_" + user.getRole().getName()) // ROLE_ADMIN / ROLE_CLIENT
                .disabled(!user.isActive())                   // compte actif ?
                .build();
    }
}
