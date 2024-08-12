package com.group.sdf.service;

import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.group.sdf.config.UserDetailsImpl;
import com.group.sdf.entity.User;
import com.group.sdf.repository.UserRepository;

@Service
public class CustomUserDetailsServiceImpl implements UserDetailsService {
	
	private final org.slf4j.Logger logger = LoggerFactory.getLogger(this.getClass());
	
	@Autowired
	UserRepository userRepository;

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		logger.info("searching for username; " + username);
		User user = userRepository.findByUsername(username).orElseThrow(() -> new UsernameNotFoundException("User Not Found with username: " + username));
		logger.info(user.getRoles().toString());
		return UserDetailsImpl.build(user);
	}

}
