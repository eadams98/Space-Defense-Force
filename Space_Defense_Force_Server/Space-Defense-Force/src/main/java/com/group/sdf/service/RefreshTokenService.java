package com.group.sdf.service;

import java.util.Optional;

import com.group.sdf.entity.RefreshToken;

public interface RefreshTokenService {
	
	public Optional<RefreshToken> findByToken(String token);

	public RefreshToken createRefreshToken(Long userId);
	
	public RefreshToken createRefreshToken(String username);
	
	public RefreshToken verifyExpiration(RefreshToken token);
	
	public void deleteByUserId(Long userId);
	
}
