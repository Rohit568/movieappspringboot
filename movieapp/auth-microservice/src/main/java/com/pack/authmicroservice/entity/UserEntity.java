package com.pack.authmicroservice.entity;



import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserEntity {
	
	private long id;
	private String username;
	private String email;
	private String password;

}
