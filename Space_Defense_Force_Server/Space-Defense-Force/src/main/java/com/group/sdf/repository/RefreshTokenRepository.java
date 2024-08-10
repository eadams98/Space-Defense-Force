package com.group.sdf.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.group.sdf.entity.RefreshToken;
import com.group.sdf.entity.User;

public interface RefreshTokenRepository extends JpaRepository<RefreshToken, Long> {
	Optional<RefreshToken> findByToken(String token);
    void deleteByUser(User user);
}
