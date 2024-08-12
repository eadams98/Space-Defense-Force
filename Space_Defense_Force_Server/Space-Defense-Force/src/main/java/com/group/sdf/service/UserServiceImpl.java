package com.group.sdf.service;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.management.RuntimeErrorException;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.group.sdf.dto.CreateUserDTO;
import com.group.sdf.dto.JwtResponse;
import com.group.sdf.dto.LoginDTO;
import com.group.sdf.entity.RefreshToken;
import com.group.sdf.entity.Role;
import com.group.sdf.entity.UnitCommander;
import com.group.sdf.entity.User;
import com.group.sdf.enums.ERole;
import com.group.sdf.exceptions.UserException;
import com.group.sdf.repository.CommanderRepository;
import com.group.sdf.repository.RoleRepository;
import com.group.sdf.repository.UserRepository;
import com.group.sdf.utility.JwtUtils;

@Service
public class UserServiceImpl implements UserService {
	
	private final org.slf4j.Logger logger = LoggerFactory.getLogger(this.getClass());
	
	@Autowired
	UserDetailsService customUserDetailsService;
	
	@Autowired
	private JwtUtils jwtUtil;
	
	@Autowired
    private RefreshTokenService refreshTokenService;
	
	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private RoleRepository roleRepository;
	
	@Autowired
	private CommanderRepository commanderRepository;

	@Override
	public JwtResponse authenticateUser(LoginDTO loginDTO) {
		String username = loginDTO.getUsername();
		UserDetails user = customUserDetailsService.loadUserByUsername(username);
		if(!user.getPassword().equals(loginDTO.getPassword()))
			throw new RuntimeException("Incorrect Username/Password");
		logger.info(user.toString());
		RefreshToken refreshToken = refreshTokenService.createRefreshToken(user.getUsername());
		
		JwtResponse response = new JwtResponse();
		response.setName(username);
		response.setJwtToken(jwtUtil.generateJwtToken(user));
		response.setRefreshToken(refreshToken.getToken());
		return response;
	}

	@Override
	public String refreshUserToken(LoginDTO loginDTO) {
		RefreshToken rToken = refreshTokenService.findByToken(loginDTO.getRefreshToken()).orElseThrow(() -> new RuntimeException("token not found"));
		refreshTokenService.verifyExpiration(rToken);
		
		return refreshTokenService.findByToken(loginDTO.getRefreshToken())
	            .map((RefreshToken refreshToken) -> refreshTokenService.verifyExpiration(refreshToken))
	            .map((RefreshToken refreshToken) -> refreshToken.getUser())
	            .map((User user) -> {
	            	UserDetails userDetails =customUserDetailsService.loadUserByUsername(user.getUsername());
	                String token = jwtUtil.generateJwtToken(userDetails);
	                return token;
	            })
	            .orElseThrow(() -> new RuntimeException("Refresh token is not in database!"));
 
	}

	@Override
	public String createUser(CreateUserDTO createUserDTO) {
		validateNewUser(createUserDTO);
		Role userRole = new Role();
		userRole.setName(ERole.ROLE_USER);
		userRole = roleRepository.save(userRole);
		Set<Role> roles = new HashSet<>();
		roles.add(userRole);
		
		UnitCommander unitCommander = new UnitCommander();
		unitCommander.setCommanderName(createUserDTO.getUsername());
		unitCommander.setCommanderPassword(createUserDTO.getPassword());
		unitCommander.setCommanderXP(0);
		unitCommander.setUnit(null);
		unitCommander.setUpgradeList(null);
		unitCommander.setUpgradeTypes(null);
		unitCommander = commanderRepository.save(unitCommander);
		
		User newUser = new User();
		newUser.setUsername(createUserDTO.getEmail());
		newUser.setPassword(createUserDTO.getPassword());
		newUser.setRoles(roles);
		newUser.setUnitCommander(unitCommander);
		newUser = userRepository.save(newUser);
		
		
		refreshTokenService.createRefreshToken(newUser.getUsername());
		
		return "success";
	}
	
	private void validateNewUser(CreateUserDTO newUser) {
		List<String> validationErrors = new ArrayList<>();
		
		if (StringUtils.isBlank(newUser.getEmail()))
			validationErrors.add("email is blank");
		
		if (StringUtils.isBlank(newUser.getUsername()))
			validationErrors.add("username is blank");
		
		if (StringUtils.isBlank(newUser.getPassword()))
			validationErrors.add("password is blank");
		
		if (!validationErrors.isEmpty())
			throw new UserException(validationErrors.toString()); // throw custom error
	}

}
