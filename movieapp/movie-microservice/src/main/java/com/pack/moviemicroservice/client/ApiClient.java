package com.pack.moviemicroservice.client;


import com.google.gson.Gson;
import com.pack.moviemicroservice.pojo.*;
import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

import org.springframework.boot.autoconfigure.AutoConfigureBefore;
import org.springframework.boot.autoconfigure.cache.CacheAutoConfiguration;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.Configuration;


@Configuration
@EnableCaching
@AutoConfigureBefore(CacheAutoConfiguration.class)
public class ApiClient {
	
	
	@Cacheable("movies")
	public static MoviePojo[] getTop100Movies() throws IOException, InterruptedException {
	
	HttpRequest request = HttpRequest.newBuilder()
			.uri(URI.create("https://imdb-top-100-movies.p.rapidapi.com/"))
			.header("X-RapidAPI-Key","3ad3e16cd4msh71e5b80aa7e2078p11e7b9jsnb7dadc9872a6")
			.header("X-RapidAPI-Host", "imdb-top-100-movies.p.rapidapi.com")
			.method("GET", HttpRequest.BodyPublishers.noBody())
			.build();
	HttpResponse<String> responses= HttpClient.newHttpClient().send(request, HttpResponse.BodyHandlers.ofString());
	 //System.out.println(responses.body());
	
	Gson g = new Gson();
	 
	 MoviePojo[] list = g.fromJson(responses.body(), MoviePojo[].class);
	 return list;
	}

}
