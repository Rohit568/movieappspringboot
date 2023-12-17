package com.pack.usermicroservice.service;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.pack.usermicroservice.entity.UserEntity;
import com.pack.usermicroservice.exception.UserAlreadyExistsException;
import com.pack.usermicroservice.repository.UserRepository;

@Service
public class UserServiceImpl implements UserService{
	
	@Autowired
	private UserRepository userRepo;
	
	@Autowired
	private PasswordEncoder passwordEncoder;
	
	@Autowired 
	private KafkaTemplate<String ,String > kafkaTemplate;

@Override
public UserEntity userSave(UserEntity userEntity) throws UserAlreadyExistsException {
	// TODO Auto-generated method stub
	UserEntity user = userRepo.findByUsername(userEntity.getUsername());
	if(user !=null)
	{
		throw new UserAlreadyExistsException("User already present");
	}
	userEntity.setPassword(passwordEncoder.encode(userEntity.getPassword()));
	UserEntity u1 = userRepo.save(userEntity);
	return u1;
}

@Override
@KafkaListener(topics = "find_entity")
public void findByUsername(String username) throws JsonProcessingException {
	// TODO Auto-generated method stub
	
	UserEntity userEntity  = userRepo.findByUsername(username);
	
    if(userEntity == null)
    {
    	kafkaTemplate.send("get_entity","not_found");
    }
    else {
    ObjectMapper mapper = new ObjectMapper();
    String stringUserEntity = mapper.writeValueAsString(userEntity);
	kafkaTemplate.send("get_entity", stringUserEntity);
	System.out.println(stringUserEntity);
    }
	
	
}

@Override
public UserEntity updateuserdetail(String username, String password) {
	// TODO Auto-generated method stub
	UserEntity userEntity  = userRepo.findByUsername(username);
	userEntity.setPassword(passwordEncoder.encode(userEntity.getPassword()));
	UserEntity u1 = userRepo.save(userEntity);
	return u1;

}

@Override
public UserEntity getuser(String username) {
	// TODO Auto-generated method stub
	UserEntity userEntity  = userRepo.findByUsername(username);
	return userEntity;
}



}
