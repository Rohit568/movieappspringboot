package com.pack.wishlistmicroservice.controller;

import java.io.IOException;
import java.util.*;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;



import com.pack.wishlistmicroservice.entity.WishList;
import com.pack.wishlistmicroservice.exception.AlreadyAddInFavouriteException;
import com.pack.wishlistmicroservice.exception.DataIsCurruptException;
import com.pack.wishlistmicroservice.exception.MovieNotFoundException;
import com.pack.wishlistmicroservice.pojo.MovieDto;
import com.pack.wishlistmicroservice.pojo.ResponseMessage;
import com.pack.wishlistmicroservice.service.WishListService;

import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import io.swagger.v3.oas.annotations.responses.ApiResponse;


@RestController
@RequestMapping("/wishlist")
public class WishListController {
	
	@Autowired
	private WishListService wishListService;
	
	
	@GetMapping("/{username}/{id}")
	@ApiResponse(description = "to add movie to the favourite using username name")
	public ResponseEntity<?> addtofavourite(@PathVariable("username") String username,@PathVariable("id") String id) throws IOException, InterruptedException, AlreadyAddInFavouriteException, DataIsCurruptException{
	
	    String msg = wishListService.addtowishtable(username, id);
		return new ResponseEntity<ResponseMessage>(new ResponseMessage(msg), HttpStatus.OK);	
	}
	

	@GetMapping("/favourites/{username}")
	public ResponseEntity<?> getallfavourite(@PathVariable String username){
		List<MovieDto> list = wishListService.findallfavouritemovies(username);
		return new ResponseEntity<List<MovieDto>>(list, HttpStatus.OK);
	}
	
	
	@GetMapping("/favourite/{id}")
	public ResponseEntity<?> getuserfavouriteid(@PathVariable long id){
		WishList wish = wishListService.findfavouritebyid(id);
		return new ResponseEntity<WishList>(wish, HttpStatus.OK);
	}
	@DeleteMapping("/remove/{id}")
	public ResponseEntity<?> deletefromfavourite(@PathVariable("id") long id) throws MovieNotFoundException{
		
		WishList wish = wishListService.removefromfavourite(id);
		return new ResponseEntity<WishList>(wish, HttpStatus.OK);
				
	}

}
