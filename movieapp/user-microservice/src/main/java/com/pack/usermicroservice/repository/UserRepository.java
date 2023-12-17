package com.pack.usermicroservice.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.pack.usermicroservice.entity.UserEntity;

public interface UserRepository extends JpaRepository<UserEntity, Long>{

	UserEntity findByUsername(String username);
}
