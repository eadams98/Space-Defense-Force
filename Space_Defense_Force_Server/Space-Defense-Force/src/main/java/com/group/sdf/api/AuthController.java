package com.group.sdf.api;

import java.util.function.Function;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.group.sdf.dto.CreateUserDTO;
import com.group.sdf.dto.JwtResponse;
import com.group.sdf.dto.LoginDTO;
import com.group.sdf.entity.User;
import com.group.sdf.service.RefreshTokenService;
import com.group.sdf.service.UserService;
import com.group.sdf.utility.JwtUtils;

import io.jsonwebtoken.Claims;

@CrossOrigin
@RestController
@RequestMapping(value="/auth")
@Validated // forgot what this does. Something to do with DTO confirmation I'm sure. // yes is for accessing validation methods on request body , and possibly path variables
public class AuthController {
	
	@Autowired
	private UserService userService;
	
	
	@Autowired
	private Environment environment;
	
	@Autowired
	private JwtUtils jwtUtil;
	
	@GetMapping(value="/test/{jwt}")
	public ResponseEntity<Claims> testJwtGeneration(@PathVariable String jwt) {
		Claims resp = jwtUtil.getClaimFromToken(jwt, Function.identity());
		return new ResponseEntity<>(resp, HttpStatus.OK);
	}
	
	@PostMapping(value="/create-user")
	public ResponseEntity<String> createUser(@RequestBody CreateUserDTO createUserDTO) {
		String response = userService.createUser(createUserDTO);
		return new ResponseEntity<>(response, HttpStatus.OK);
	}
	
	@PostMapping(value="/signin")
	public ResponseEntity<JwtResponse> authenticateUser(@RequestBody LoginDTO loginRequest) throws Exception {
		JwtResponse response = userService.authenticateUser(loginRequest);
		return new ResponseEntity<>(response, HttpStatus.OK);
	}
	
	@PostMapping("/refresh-token")
	public ResponseEntity<String> refreshToken(@RequestBody LoginDTO request) {
		String token = userService.refreshUserToken(request);
		return new ResponseEntity<>(token, HttpStatus.OK);
		
	}
	
}
