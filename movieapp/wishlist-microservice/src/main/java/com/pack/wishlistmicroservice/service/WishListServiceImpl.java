package com.pack.wishlistmicroservice.service;

import java.io.IOException;
import java.time.LocalDate;
import java.util.*;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.pack.wishlistmicroservice.client.MovieClient;

import com.pack.wishlistmicroservice.entity.WishList;
import com.pack.wishlistmicroservice.exception.AlreadyAddInFavouriteException;
import com.pack.wishlistmicroservice.exception.DataIsCurruptException;
import com.pack.wishlistmicroservice.exception.MovieNotFoundException;
import com.pack.wishlistmicroservice.pojo.MovieDto;
import com.pack.wishlistmicroservice.pojo.MoviePojo;
import com.pack.wishlistmicroservice.pojo.ResponseMessage;

import com.pack.wishlistmicroservice.repository.WishListREpository;

import feign.FeignException;
import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import jakarta.transaction.Transactional;


@Service
public class WishListServiceImpl implements WishListService {

	@Autowired 
	private MovieClient movieClient;
	
	@Autowired
	private WishListREpository wishlistRepo;
	
	
	@CircuitBreaker(name = "wishlist-service", fallbackMethod = "defaultwishlistmethod")
	@Override
	public String addtowishtable(String username, String id) throws IOException, InterruptedException, AlreadyAddInFavouriteException, DataIsCurruptException {
		// TODO Auto-generated method stub
		
		Optional<WishList> wish = wishlistRepo.findByUsernameAndMovieId(username, id);
		if(wish.isPresent())
		{
			throw new AlreadyAddInFavouriteException("Already added to the favourites");
		}
		
		ResponseEntity<MoviePojo> rmp = (ResponseEntity<MoviePojo>) movieClient.findmoviebyid(id);
		ObjectMapper mapper = new ObjectMapper();
		MoviePojo mp= mapper.convertValue(rmp.getBody(), MoviePojo.class);
		
		if(mp == null) {
		    throw new DataIsCurruptException("data is null");
		}
	    
		WishList  wishlist =  WishList.builder()
				      .movieId(mp.getId())
                      .username(username)
                      .rankGiven(mp.getRank())
                      .description(mp.getDescription())
                      .imageName(mp.getImage())
                      .bigImageName(mp.getBig_image())
                      .titleName(mp.getTitle())
                      .ratingGiven(mp.getRating())
                      .yearAdded(mp.getYear())
                      .dateAdded(LocalDate.now())
                      .build();
		
		WishList w = wishlistRepo.save(wishlist);
		
		return "Added to the favourite!";
	    
		
		
	}
     public String defaultwishlistmethod(Exception e){
		
		String msg = "something is wrong";
		return msg;
		//return new ResponseEntity<>(new ResponseMessage(msg), HttpStatus.FAILED_DEPENDENCY);
	}

    @CachePut(value = "movies", key = "#username")
	@Override
	public List<MovieDto> findallfavouritemovies(String username) {
		// TODO Auto-generated method stub
		List<WishList> list =  wishlistRepo.findByUsername(username);
		
		List<MovieDto> list2 = new ArrayList<>();
		
		
		for(WishList wl : list) {
			
			MovieDto d = MovieDto.builder()
					.id(wl.getId())
					.image(wl.getImageName())
					.ranking(wl.getRankGiven())
					.rating(wl.getRatingGiven())
					.title(wl.getTitleName())
					.year(wl.getYearAdded())
					.build();
					
					
			list2.add(d);
		}
		
		return list2;
		
	}

	@Override
	public WishList removefromfavourite(Long id) throws MovieNotFoundException {
		// TODO Auto-generated method stub
		Optional<WishList>  l = wishlistRepo.findById(id);
		if(l.isPresent())
		{
		     wishlistRepo.deleteById(id);
		     return l.get();
		}
		else
		{
			throw new MovieNotFoundException("Movie is not found !");
		}
	}
	@Override
	public WishList findfavouritebyid(long id) {
		Optional<WishList> movie = wishlistRepo.findById(id);
		if(movie.isPresent())
			return movie.get();
		
		return null;
	}
	
}
