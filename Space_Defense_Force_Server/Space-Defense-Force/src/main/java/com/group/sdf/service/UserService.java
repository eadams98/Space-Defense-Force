package com.group.sdf.service;

import com.group.sdf.dto.LoginDTO;
import com.group.sdf.dto.JwtResponse;

public interface UserService {

	JwtResponse authenticateUser(LoginDTO loginDTO);
		
}
