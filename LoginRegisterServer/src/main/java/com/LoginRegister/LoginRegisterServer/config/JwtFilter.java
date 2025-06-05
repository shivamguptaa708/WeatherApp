package com.LoginRegister.LoginRegisterServer.config;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import com.LoginRegister.LoginRegisterServer.service.JWTService;
import com.LoginRegister.LoginRegisterServer.service.MyUserDetailsService;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@Component
public class JwtFilter extends OncePerRequestFilter{
	
	@Autowired
	private JWTService jwtService;
	
	@Autowired
	ApplicationContext context;
	
	

	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
			throws ServletException, IOException {
		
		response.setHeader("Access-Control-Allow-Origin", "http://localhost:5173");
		response.setHeader("Access-Control-Allow-Methods", "GET, POST, PUT, DELETE, OPTIONS");
		response.setHeader("Access-Control-Allow-Headers", "Authorization, Content-Type");
		response.setHeader("Access-Control-Allow-Credentials", "true");

		if ("OPTIONS".equalsIgnoreCase(request.getMethod())) {
		    response.setStatus(HttpServletResponse.SC_OK);
		    return;
		}

		
		String authHeader = request.getHeader("Authorization");
		String token = null;
		String username = null; 
		
		if(authHeader != null && authHeader.startsWith("Bearer "))
		{
			token = authHeader.substring(7);
			username = jwtService.extractUserName(token);
		}
		
		if(username != null && SecurityContextHolder.getContext().getAuthentication() == null)
		{
			UserDetails userDetails = context.getBean(MyUserDetailsService.class).loadUserByUsername(username);
			
			if(jwtService.validateToken(token, userDetails))
			{
				UsernamePasswordAuthenticationToken authtoken = new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities());
				authtoken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
				SecurityContextHolder.getContext().setAuthentication(authtoken);
			}
		}
		
		filterChain.doFilter(request, response);
		
		
	}
	

}
