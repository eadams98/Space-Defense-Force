package com.group.sdf.service;

import com.group.sdf.dto.LoginDTO;
import com.group.sdf.entity.User;
import com.group.sdf.dto.CreateUserDTO;
import com.group.sdf.dto.JwtResponse;

public interface UserService {

	JwtResponse authenticateUser(LoginDTO loginDTO);
	
	String refreshUserToken(LoginDTO loginDTO);
	
	String createUser(CreateUserDTO createUserDTO);
		
}
