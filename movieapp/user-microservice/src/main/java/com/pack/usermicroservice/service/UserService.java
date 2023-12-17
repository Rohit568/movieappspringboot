package com.pack.usermicroservice.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.pack.usermicroservice.entity.UserEntity;
import com.pack.usermicroservice.exception.UserAlreadyExistsException;

public interface UserService {
     
	UserEntity userSave(UserEntity u) throws UserAlreadyExistsException;
	
	void findByUsername(String username) throws JsonProcessingException;

	UserEntity updateuserdetail(String username, String password);

	UserEntity getuser(String username);
}
