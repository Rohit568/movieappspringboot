package com.pack.authmicroservice.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.pack.authmicroservice.dto.TokenDto;
import com.pack.authmicroservice.dto.TokenValidatorDto;
import com.pack.authmicroservice.entity.AuthRequest;
import com.pack.authmicroservice.exception.UsernameOrPasswordInvalidException;
import com.pack.authmicroservice.service.AuthService;



@RestController
@RequestMapping("/auth")
public class AuthController {
	

        private static Logger log = LoggerFactory.getLogger(AuthController.class);
	
	    @Autowired
	    private AuthService service;

	    @Autowired
	    private AuthenticationManager authenticationManager;


	    @PostMapping("/token")
	    public ResponseEntity<?> getToken(@RequestBody AuthRequest authRequest) throws UsernameOrPasswordInvalidException {
	        Authentication authenticate = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(authRequest.getUsername(), authRequest.getPassword()));
	        if (authenticate.isAuthenticated()) {
	            String token = service.generateToken(authRequest.getUsername());
	            log.info(HttpStatus.CREATED + "! user authenticated");
	            return new ResponseEntity<TokenDto>(new TokenDto(token), HttpStatus.CREATED);
	        } else {
	        	log.error("Either password or username wrong");
	            throw new UsernameOrPasswordInvalidException("Invalid Login");
	        }
	    }

	    @GetMapping("/validate")
	    public ResponseEntity<?> validateToken(@RequestHeader(value = "Authorization" , required = true) String token) {
	    	try {
	         service.validateToken(token);
	         log.info("Token  is valid and return");
	         return new ResponseEntity<TokenValidatorDto>(new TokenValidatorDto(true), HttpStatus.OK);
	    	}
	    	catch(Exception e) {
	    		log.error("Token is not valid");
	    		throw new RuntimeException("Token is not valid");
	    	}
	        
	    }
   
}
