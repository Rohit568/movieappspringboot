package com.pack.moviemicroservice.service;

import java.io.IOException;


import com.pack.moviemicroservice.pojo.MoviePojo;

public interface MovieService {
	
	
	public MoviePojo searchMovie(String id) throws IOException, InterruptedException;
	
}
