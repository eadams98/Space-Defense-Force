package com.group.sdf.service;

import java.time.Instant;
import java.util.Optional;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.group.sdf.entity.RefreshToken;
import com.group.sdf.repository.RefreshTokenRepository;
import com.group.sdf.repository.UserRepository;

@Service
public class RefreshTokenServiceImpl implements RefreshTokenService {
	
	@Autowired
    private RefreshTokenRepository refreshTokenRepository;

    @Autowired
    private UserRepository userRepository;

    private final static int MS_TO_SECOND = 1000;
    private final static int SECOND_TO_MIN = 60;
    private final static int MIN_TO_HOUR = 60;
    private static final long REFRESH_TOKEN_EXPIRATION = MS_TO_SECOND * SECOND_TO_MIN * MIN_TO_HOUR; //1 hour

    @Override
    public Optional<RefreshToken> findByToken(String token) {
        return refreshTokenRepository.findByToken(token);
    }

	@Override
	public RefreshToken createRefreshToken(Long userId) {
		RefreshToken refreshToken = new RefreshToken();
        refreshToken.setUser(userRepository.findById(userId).orElseThrow(() -> new RuntimeException("User not found")));
        refreshToken.setToken(UUID.randomUUID().toString()); // should i use another JWT?
        refreshToken.setExpiryDate(Instant.now().plusMillis(REFRESH_TOKEN_EXPIRATION));
        return refreshTokenRepository.save(refreshToken);
	}
	
	@Override
	public RefreshToken createRefreshToken(String username) {
		RefreshToken refreshToken = new RefreshToken();
        refreshToken.setUser(userRepository.findByUsername(username).orElseThrow(() -> new RuntimeException("User not found to create refreshToken")));
        refreshToken.setToken(UUID.randomUUID().toString()); // should i use another JWT?
        refreshToken.setExpiryDate(Instant.now().plusMillis(REFRESH_TOKEN_EXPIRATION));
        return refreshTokenRepository.save(refreshToken);
	}

	@Override
	public RefreshToken verifyExpiration(RefreshToken token) {
		if (token.getExpiryDate().compareTo(Instant.now()) < 0) {
            refreshTokenRepository.delete(token);
            throw new RuntimeException("Refresh token was expired. Please make a new login request");
        }
        return token;
	}

	@Override
	public void deleteByUserId(Long userId) {
		refreshTokenRepository.deleteByUser(userRepository.findById(userId).orElseThrow(() -> new RuntimeException("User not found")));		
	}

}
