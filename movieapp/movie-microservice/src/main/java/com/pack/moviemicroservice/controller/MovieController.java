package com.pack.moviemicroservice.controller;

import java.io.IOException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.pack.moviemicroservice.client.ApiClient;
import com.pack.moviemicroservice.pojo.MoviePojo;
import com.pack.moviemicroservice.service.MovieService;


@RestController
@RequestMapping("/movie")
public class MovieController {
	
	public static Logger log = LoggerFactory.getLogger(MovieController.class);

	@Autowired
	private MovieService movieService;
	
	@GetMapping("/api")
	public ResponseEntity<?> viewtop100movies() throws IOException, InterruptedException {
	    MoviePojo[] mp =  ApiClient.getTop100Movies();
	    log.info("return all top 100 movies");
	    return new ResponseEntity<MoviePojo[]>(mp,HttpStatus.OK);
		
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<?> findmoviebyid(@PathVariable("id") String id) throws IOException, InterruptedException{
		
		MoviePojo mp = movieService.searchMovie(id);
		log.info("search movie by Id ");
		return new ResponseEntity<MoviePojo>(mp, HttpStatus.OK);
		
	}
	
}
