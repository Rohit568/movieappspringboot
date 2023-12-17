package com.pack.usermicroservice.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


import com.pack.usermicroservice.entity.UserEntity;
import com.pack.usermicroservice.exception.UserAlreadyExistsException;
import com.pack.usermicroservice.pojo.UpdatedPassword;
import com.pack.usermicroservice.service.UserService;

import jakarta.ws.rs.Path;


@RestController
@RequestMapping("/user")
public class UserController {
	
	public static Logger log = LoggerFactory.getLogger(UserController.class);
	
	
	@Autowired
	private UserService userService;
	
	@PostMapping("/register")
	public ResponseEntity<?> adduser(@RequestBody UserEntity user) throws UserAlreadyExistsException
	{
		System.out.println(user.toString());
		UserEntity u = userService.userSave(user);
		  
		ResponseEntity<UserEntity> entity  = new ResponseEntity<UserEntity>(u, HttpStatus.OK);
		log.info("new user added ");
		return entity;
	}
	
	@GetMapping("/detail/{username}")
	public ResponseEntity<?> getdetail(@PathVariable("username")String  username)
	{
		UserEntity u = userService.getuser(username);
		ResponseEntity<UserEntity> entity  = new ResponseEntity<UserEntity>(u, HttpStatus.OK);
		return entity;
	}
	
	@PutMapping("/update")
	public ResponseEntity<?> updatepassword(@RequestBody UpdatedPassword updatedPassword){
		
		UserEntity u = userService.updateuserdetail(updatedPassword.getUsername(), updatedPassword.getPassword());
		ResponseEntity<UserEntity> entity  = new ResponseEntity<UserEntity>(u, HttpStatus.OK);
		
		return entity;
		
	}
	
	
	

}
