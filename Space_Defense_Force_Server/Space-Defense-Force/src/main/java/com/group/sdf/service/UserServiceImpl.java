package com.group.sdf.service;

import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.group.sdf.dto.JwtResponse;
import com.group.sdf.dto.LoginDTO;
import com.group.sdf.entity.User;
import com.group.sdf.utility.JwtUtils;

@Service
public class UserServiceImpl implements UserService {
	
	private final org.slf4j.Logger logger = LoggerFactory.getLogger(this.getClass());
	
	@Autowired
	UserDetailsService customUserDetailsService;
	
	@Autowired
	private JwtUtils jwtUtil;

	@Override
	public JwtResponse authenticateUser(LoginDTO loginDTO) {
		String username = loginDTO.getUsername();
		UserDetails user = customUserDetailsService.loadUserByUsername(username);
		logger.info(user.toString());
		
		JwtResponse response = new JwtResponse();
		response.setName(username);
		response.setJwtToken(jwtUtil.generateJwtToken(user));
		return response;
	}

}
