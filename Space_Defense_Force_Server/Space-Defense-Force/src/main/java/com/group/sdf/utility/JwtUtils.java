package com.group.sdf.utility;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import com.group.sdf.config.UserDetailsImpl;

import io.jsonwebtoken.*;

@Component
public class JwtUtils {
	
	private final org.slf4j.Logger logger = LoggerFactory.getLogger(this.getClass());
	
	@Value("${app.jwtSecret}")
	private String jwtSecret;
	
	private final static int MS_TO_SECOND = 1000;
    private final static int SECOND_TO_MIN = 60;
    private final static int TEN = 1;
	//@Value("{app.jwtExpirationMs}")
	private int jwtExpirationMs = MS_TO_SECOND * SECOND_TO_MIN * TEN; // 10 min
	
	public String generateJwtToken(UserDetailsImpl userDetails) {
		Map<String, Object> claims = new HashMap<>();
		claims.put("role", userDetails.getAuthorities().toArray().toString());
		claims.put("commanderId", userDetails.getCommanderId());
		return doGenerateToken(claims, userDetails.getUsername());

	}
	
	private String doGenerateToken(Map<String, Object> claims, String subject) {

		return Jwts.builder().setClaims(claims).setSubject(subject).setIssuedAt(new Date(System.currentTimeMillis()))
				.setExpiration(new Date((new Date()).getTime() + jwtExpirationMs)).signWith(SignatureAlgorithm.HS512, jwtSecret)
		        .compact();
				//.setExpiration(new Date(System.currentTimeMillis() + JWT_TOKEN_VALIDITY*1000)).signWith(SignatureAlgorithm.HS512, secret).compact();
	}
	
	public String getRoleFromToken(String token) throws ExpiredJwtException, UnsupportedJwtException, MalformedJwtException, SignatureException, IllegalArgumentException {
		logger.info("clams: "+ getAllClaimsFromToken(token).toString());
		
		return getAllClaimsFromToken(token).get("role", String.class);
	}

	public String getUsernameFromToken(String token) throws ExpiredJwtException, UnsupportedJwtException, MalformedJwtException, SignatureException, IllegalArgumentException {
		return getClaimFromToken(token, Claims::getSubject);
	}

	public Date getIssuedAtDateFromToken(String token) throws ExpiredJwtException, UnsupportedJwtException, MalformedJwtException, SignatureException, IllegalArgumentException {
		return getClaimFromToken(token, Claims::getIssuedAt);
	}

	public Date getExpirationDateFromToken(String token) throws ExpiredJwtException, UnsupportedJwtException, MalformedJwtException, SignatureException, IllegalArgumentException {
		return getClaimFromToken(token, Claims::getExpiration);
	}
	
	public Integer getCommanderIdFromToken(String token) throws ExpiredJwtException, UnsupportedJwtException, MalformedJwtException, SignatureException, IllegalArgumentException {
		return getAllClaimsFromToken(token).get("commanderId", Integer.class);
	}

	public <T> T getClaimFromToken(String token, Function<Claims, T> claimsResolver) {
		final Claims claims = getAllClaimsFromToken(token);
		return claimsResolver.apply(claims);
	}

	private Claims getAllClaimsFromToken(String token) throws ExpiredJwtException, UnsupportedJwtException, MalformedJwtException, SignatureException, IllegalArgumentException {
		//try {
			return Jwts.parser().setSigningKey(jwtSecret).parseClaimsJws(token).getBody();
		/*} catch (Exception ex) {
			logger.info("getAllClaimsFromToken Error: " + ex.getMessage());
			handlerExceptionResolver.resolveException(null, null, null, ex);
			return null;
		}*/
	}

	private Boolean isTokenExpired(String token) {
		final Date expiration = getExpirationDateFromToken(token);
		return expiration.before(new Date());
	}

	private Boolean ignoreTokenExpiration(String token) {
		// here you specify tokens, for that the expiration is ignored
		return false;
	}

	public Boolean canTokenBeRefreshed(String token) {
		return (!isTokenExpired(token) || ignoreTokenExpiration(token));
	}

	public Boolean validateToken(String token, UserDetails userDetails) {
		final String username = getUsernameFromToken(token);
		return (username.equals(userDetails.getUsername()) && !isTokenExpired(token));
	}
}
