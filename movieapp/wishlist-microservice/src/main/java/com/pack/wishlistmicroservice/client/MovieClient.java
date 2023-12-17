package com.pack.wishlistmicroservice.client;

import java.io.IOException;


import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;


import com.pack.wishlistmicroservice.pojo.ResponseMessage;

@FeignClient(value="movie", url = "http://localhost:8082/movie")
public interface MovieClient {

	@GetMapping("/{id}")
	public ResponseEntity<?> findmoviebyid(@PathVariable("id") String id) throws IOException, InterruptedException;
		
}
