package com.pack.usermicroservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication(exclude = { SecurityAutoConfiguration.class })
public class UserMicroserviceApplication {

	public static void main(String[] args) {
		SpringApplication.run(UserMicroserviceApplication.class, args);
	}

}
