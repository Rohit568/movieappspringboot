package com.pack.wishlistmicroservice.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.pack.wishlistmicroservice.entity.WishList;

@Repository
public interface WishListREpository extends JpaRepository<WishList, Long> {
	
	Optional<WishList> findByUsernameAndMovieId(String username, String id);

	List<WishList> findByUsername(String username);

}
