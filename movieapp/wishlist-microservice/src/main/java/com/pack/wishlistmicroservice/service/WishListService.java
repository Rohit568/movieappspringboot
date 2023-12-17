package com.pack.wishlistmicroservice.service;

import java.io.IOException;
import java.util.List;

import com.pack.wishlistmicroservice.entity.WishList;
import com.pack.wishlistmicroservice.exception.AlreadyAddInFavouriteException;
import com.pack.wishlistmicroservice.exception.DataIsCurruptException;
import com.pack.wishlistmicroservice.exception.MovieNotFoundException;
import com.pack.wishlistmicroservice.pojo.MovieDto;


public interface WishListService {

	String addtowishtable(String username, String id) throws IOException, InterruptedException, AlreadyAddInFavouriteException, DataIsCurruptException;
	List<MovieDto> findallfavouritemovies(String username);
	WishList removefromfavourite(Long id) throws MovieNotFoundException;
	
	WishList findfavouritebyid(long id);

}
