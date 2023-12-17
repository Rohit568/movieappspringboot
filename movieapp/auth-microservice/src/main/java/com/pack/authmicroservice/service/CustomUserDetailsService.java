package com.pack.authmicroservice.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.pack.authmicroservice.entity.CustomUserDetails;
import com.pack.authmicroservice.entity.UserEntity;

public class CustomUserDetailsService implements UserDetailsService {
     
//	@Autowired
//	private UserRepository userRepo;
	
	@Autowired
	private KafkaTemplate<String, String> kafkaTemplate;
	private UserEntity user = null;
	
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		// TODO Auto-generated method stub
		
		
		kafkaTemplate.send("find_entity", username);
		
		//Optional<UserEntity> user = userRepo.findByUsername(username);
		try {
            // Sleep for 2 seconds (2000 milliseconds)
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            // Handle the exception (e.g., log it or print a message)
            e.printStackTrace();
        }
   
	    if(user != null)
	    {
	    	UserDetails u = new CustomUserDetails(user);
	    	
	    	user = null;
	    	
	    	return u;
	    	
	    }
	    else 
	    	throw new UsernameNotFoundException("User is not registered");
		
		
		
	}
	
	@KafkaListener(topics = "get_entity")
	public void setUserEntity(String u) throws JsonMappingException, JsonProcessingException {
	   
		 ObjectMapper objectMapper = new ObjectMapper();
		 System.out.println(u);
         // u = "{" + u + "}";
	        // Deserialize the input string into a UserEntity object
	     UserEntity user1 = objectMapper.readValue(u, UserEntity.class);
	     System.out.println(user1.getEmail() + "\n" + user1.getPassword());
	     
	     user = user1;
	     
	}

}
