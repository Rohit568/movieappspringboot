package com.pack.moviemicroservice.service;

import java.io.IOException;
import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.stereotype.Service;

import com.pack.moviemicroservice.client.ApiClient;
import com.pack.moviemicroservice.pojo.MoviePojo;


@Service
public class MovieServiceImpl implements MovieService {

    @Override
	public MoviePojo searchMovie(String id) throws IOException, InterruptedException {
		// TODO Auto-generated method stub
		MoviePojo[] mps  = ApiClient.getTop100Movies();
		return Arrays.stream(mps).filter(m -> m.getId().equals(id)).findAny().orElse(null);
		 
	}	

}
