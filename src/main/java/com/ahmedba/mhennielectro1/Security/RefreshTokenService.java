package com.ahmedba.mhennielectro1.Security;

import com.ahmedba.mhennielectro1.Entities.RefreshToken;
import com.ahmedba.mhennielectro1.Entities.Users;
import com.ahmedba.mhennielectro1.Repositories.RefreshTokenRepository;
import com.ahmedba.mhennielectro1.Repositories.UserRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.Instant;
import java.util.UUID;

@Service
public class RefreshTokenService {

    // ⏱️ 7 jours
    private static final long REFRESH_TOKEN_DURATION_SECONDS = 7 * 24 * 60 * 60;

    private final RefreshTokenRepository refreshTokenRepository;
    private final UserRepository userRepository;

    public RefreshTokenService(RefreshTokenRepository refreshTokenRepository,
                               UserRepository userRepository) {
        this.refreshTokenRepository = refreshTokenRepository;
        this.userRepository = userRepository;
    }

    /**
     * 🔹 Crée un refresh token pour un user
     * 🔹 1 user = 1 refresh token (ancien supprimé)
     */
    @Transactional
    public RefreshToken createRefreshToken(Long userId) {

        Users user = userRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("User not found"));

        // Supprimer les anciens refresh tokens du user
        refreshTokenRepository.deleteByUsers(user);

        RefreshToken refreshToken = new RefreshToken();
        refreshToken.setUsers(user);
        refreshToken.setToken(UUID.randomUUID().toString());
        refreshToken.setExpiryDate(
                Instant.now().plusSeconds(REFRESH_TOKEN_DURATION_SECONDS)
        );

        return refreshTokenRepository.save(refreshToken);
    }

    /**
     * 🔹 Vérifie si le refresh token est expiré
     */
    public RefreshToken verifyExpiration(RefreshToken refreshToken) {

        if (refreshToken.getExpiryDate().isBefore(Instant.now())) {
            refreshTokenRepository.delete(refreshToken);
            throw new RuntimeException("Refresh token expired. Please login again.");
        }

        return refreshToken;
    }

    /**
     * 🔹 Supprime les refresh tokens d’un user (logout)
     */
    @Transactional
    public void deleteByUser(Users user) {
        refreshTokenRepository.deleteByUsers(user);
    }
}
