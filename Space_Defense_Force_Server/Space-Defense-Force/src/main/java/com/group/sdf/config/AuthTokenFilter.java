package com.group.sdf.config;

import java.io.IOException;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.filter.OncePerRequestFilter;
import org.springframework.web.servlet.HandlerExceptionResolver;

import com.group.sdf.exceptions.JwtTokenValidationException;
import com.group.sdf.utility.JwtUtils;

import io.jsonwebtoken.ExpiredJwtException;

@Component
public class AuthTokenFilter extends OncePerRequestFilter{
	
	private final org.slf4j.Logger logger = LoggerFactory.getLogger(this.getClass());

	@Autowired
	private JwtUtils jwtUtils;
	
	@Autowired
	private UserDetailsService userDetailsService;
	
	@Autowired
	private HandlerExceptionResolver handlerExceptionResolver;


	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
			throws ServletException, IOException {
		
		String username = null;
		String jwt = null;
		
		try {
			
			jwt = parseJwt(request);
			
			if (jwt != null ){//&& jwtUtils.validateToken(jwt, )) {
				username = jwtUtils.getUsernameFromToken(jwt);
			}
			
		} catch (IllegalArgumentException e) {
			System.out.println("Unable to get JWT Token");
			//throw new JwtTokenValidationException(e.getMessage());
			handlerExceptionResolver.resolveException(request, response, null, new JwtTokenValidationException("Unable to get JWT token"));
			return;
		} catch (ExpiredJwtException e) {
			System.out.println("JWT Token has expired");
			handlerExceptionResolver.resolveException(request, response, null, new JwtTokenValidationException("Expired JWT token"));
			return;
			//throw new RuntimeException(new JwtTokenValidationException("Expired JWT token")); // throw as runtime exception
		} catch (Exception e) {
			System.out.println("general exception JWT Token");
			handlerExceptionResolver.resolveException(request, response, null, e);
			return;
		}
		
		if (username != null) {
			
			
			logger.info(request.getRequestURI());
			UserDetails userDetails;
			
			try {
				userDetails = userDetailsService.loadUserByUsername(username);
			} catch( Exception ex) {
				System.out.println("user not found in authTokenFilter");
				handlerExceptionResolver.resolveException(request, response, null, ex);
				return;
        	}
			
			// if token is valid configure Spring Security to manually set authentication
			if (jwtUtils.validateToken(jwt, userDetails)) {

				UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken = new UsernamePasswordAuthenticationToken(
						userDetails, null, userDetails.getAuthorities());
				usernamePasswordAuthenticationToken
						.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
				// After setting the Authentication in the context, we specify
				// that the current user is authenticated. So it passes the Spring Security Configurations successfully.
				SecurityContextHolder.getContext().setAuthentication(usernamePasswordAuthenticationToken);
			}
			logger.info(userDetails.getAuthorities().toString());

		} 
		
		filterChain.doFilter(request, response);
		
	}
	
	private String parseJwt(HttpServletRequest request) {
		String headerAuth = request.getHeader("Authorization");
		
		if (StringUtils.hasText(headerAuth) && headerAuth.startsWith("Bearer "))
			return headerAuth.substring(7);
		
		return null;
	}
	
}
