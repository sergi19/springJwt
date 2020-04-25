package com.mycompany.security;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

@Component
public class TokenUtil {
	
	private long TOKEN_EXPIRATION = 1000 * 60 * 60 * 24 * 1;
	private String TOKEN_SECRET = "sacf1910";
	
	public String generateToken(UserDetails userDetails) {
		Map<String, Object> claims = new HashMap<>();
		claims.put("sub", userDetails.getUsername());
		claims.put("created", new Date());
		
		return Jwts.builder()
				.setClaims(claims)
				.setExpiration(generateExpirationDate())
				.signWith(SignatureAlgorithm.HS512, TOKEN_SECRET)
				.compact();
	}
	
	public String getUsernameFromToken(String token) {
		try {
			Claims claims = getClaims(token);
			return claims.getSubject();
		} catch(Exception e) {
			return null;
		}
	}

	public Date generateExpirationDate() {
		return new Date(System.currentTimeMillis() + TOKEN_EXPIRATION);
	}
	
	public boolean isTokenValid(String token, UserDetails userDetails) {
		String username = getUsernameFromToken(token);
		return (username.equals(userDetails.getUsername()) && !isTokenExpired(token));
	}
	
	private boolean isTokenExpired(String token) {
		Date tokenExpiration = getClaims(token).getExpiration();
		System.err.println("Date: " + tokenExpiration);
		return tokenExpiration.before(new Date());
	}

	public Claims getClaims(String token) {
		Claims claims;
		try {
			claims = Jwts.parser().setSigningKey(TOKEN_SECRET)
					.parseClaimsJws(token)
					.getBody();
		} catch (Exception ex) {
			claims = null;
		}
		return claims;
	}

}
