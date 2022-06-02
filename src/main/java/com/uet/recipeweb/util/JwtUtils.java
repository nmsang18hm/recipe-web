package com.uet.recipeweb.util;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.MalformedJwtException;
import io.jsonwebtoken.SignatureException;
import io.jsonwebtoken.UnsupportedJwtException;

@Component
public class JwtUtils {
	private static final Logger LOGGER = LoggerFactory.getLogger(JwtUtils.class);
	
	@Value("${bezkoder.app.jwtSecret}")
	private String jwtSecret;
	
	public boolean validateJwtToken(String authToken) {
		try {
			Jwts.parser().setSigningKey(jwtSecret.getBytes()).parseClaimsJws(authToken);
			return true;
		} catch (SignatureException e) {
			LOGGER.error("Invalid JWT signature: {}", e.getMessage());
		} catch (MalformedJwtException e) {
			LOGGER.error("Invalid JWT token: {}", e.getMessage());
		} catch (ExpiredJwtException e) {
			LOGGER.error("JWT token is expired: {}", e.getMessage());
		} catch (UnsupportedJwtException e) {
			LOGGER.error("JWT token is unsupported: {}", e.getMessage());
		} catch (IllegalArgumentException e) {
			LOGGER.error("JWT claims string is empty: {}", e.getMessage());
		}
		return false;
	}
	
	public String getRoleFromJwtToken(String token) {
		return (String) Jwts.parser().setSigningKey(jwtSecret.getBytes()).parseClaimsJws(token).getBody().get("role");
	}
	
	public Object getIdFromJwtToken(String token) {
		return Jwts.parser().setSigningKey(jwtSecret.getBytes()).parseClaimsJws(token).getBody().get("user_id");
	}
	
	public String parseJwt(HttpServletRequest request) {
		String headerAuth = request.getHeader("Authorization");
		
		if (StringUtils.hasText(headerAuth) && headerAuth.startsWith("Bearer ")) {
			return headerAuth.substring(7, headerAuth.length());
		}
		
		return null;
	}
}
