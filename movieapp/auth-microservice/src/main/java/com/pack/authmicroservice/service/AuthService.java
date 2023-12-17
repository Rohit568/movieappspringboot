package com.pack.authmicroservice.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;



@Service
public class AuthService {
	
	
	
	
	@Autowired
	private PasswordEncoder passwordEncoder;
	
	@Autowired
	private JwtService jwtService;
	
	
	 public String generateToken(String username) {
	        return jwtService.generateToken(username);
	    }

	    public void validateToken(String token) {
	        jwtService.validateToken(token);
	    }
	
}
