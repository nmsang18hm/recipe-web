package com.uet.recipeweb.config;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.web.filter.OncePerRequestFilter;

import com.uet.recipeweb.util.JwtUtils;;

public class AuthTokenFilter extends OncePerRequestFilter {
	
	private static final Logger LOGGER = LoggerFactory.getLogger(AuthTokenFilter.class);
	
	@Autowired
	private JwtUtils jwtUtils;

	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
			throws ServletException, IOException {
		
		try {
			String jwt = jwtUtils.parseJwt(request);
			if (jwt != null && jwtUtils.validateJwtToken(jwt)) {
				Object id = jwtUtils.getIdFromJwtToken(jwt);
				String role = jwtUtils.getRoleFromJwtToken(jwt);
				List<GrantedAuthority> authorities = new ArrayList<>();
				authorities.add(new SimpleGrantedAuthority(role));
				
				UsernamePasswordAuthenticationToken auth = new UsernamePasswordAuthenticationToken(id, null, authorities);
				auth.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
				
				SecurityContextHolder.getContext().setAuthentication(auth);
			}
		} catch (Exception e) {
			LOGGER.error("Cannot set user authentication: {}", e);
		}
		
		filterChain.doFilter(request, response);
	}
	
}
