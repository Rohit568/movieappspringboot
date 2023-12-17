package com.pack.wishlistmicroservice.service;


import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.Assert.assertTrue;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.junit.Test;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.pack.wishlistmicroservice.client.MovieClient;
import com.pack.wishlistmicroservice.entity.WishList;
import com.pack.wishlistmicroservice.exception.AlreadyAddInFavouriteException;
import com.pack.wishlistmicroservice.exception.DataIsCurruptException;
import com.pack.wishlistmicroservice.exception.MovieNotFoundException;
import com.pack.wishlistmicroservice.pojo.MovieDto;
import com.pack.wishlistmicroservice.pojo.MoviePojo;
import com.pack.wishlistmicroservice.repository.WishListREpository;


@RunWith(SpringRunner.class)
@SpringBootTest
public class WishListImplServiceTest {
	
	
    @InjectMocks
	private WishListServiceImpl wishlistService;
	@Mock
	private WishListREpository wishRepo;
	@Mock
	private MovieClient movieClient;
	
	AutoCloseable autoCloseable;
	
	WishList wishlist;
	MoviePojo moviePojo;
	
	@BeforeEach
	public void setup() {
		
		autoCloseable  = MockitoAnnotations.openMocks(this);
		wishlist  = WishList.builder()
				 .movieId("top2")
                 .username("ankit")
                 .rankGiven(3)
                 .description("it is based on true story")
                 .imageName("image")
                 .bigImageName("big_image")
                 .titleName("Tears of the sun")
                 .ratingGiven("8.7")
                 .yearAdded(2003)
                 .dateAdded(LocalDate.now())
                 .build();
		
	
	}
	
	@AfterEach
	public void unsetup() throws Exception {
		autoCloseable.close();
	}
   
    
    @Test
    public void testaddtowishtable_withException() throws IOException, InterruptedException, AlreadyAddInFavouriteException, DataIsCurruptException
    {
    	//Optional<WishList> o = wishRepo.findByUsernameAndMovieId("ankit", "top2");
    	WishList  wishlist =  WishList.builder()
			      .movieId("top2")
            .username("ankit")
            .rankGiven(1)
            .description("tear of the sun is good movie")
            .imageName("image")
            .bigImageName("big_image")
            .titleName("tears of the sun")
            .ratingGiven("8.5")
            .yearAdded(1992)
            .dateAdded(LocalDate.now())
            .build();
		
      wishRepo.save(wishlist);   
    	assertThrows(Exception.class, ()->{
    		wishlistService.addtowishtable("ankit", "top2");
    	});
    	
    }
    @Test
    public void testaddtowishtable() throws IOException, InterruptedException, AlreadyAddInFavouriteException, DataIsCurruptException {
    	
    	
		when(wishRepo.save(wishlist)).thenReturn(wishlist);
		assertThat(wishlistService.addtowishtable("ankit","top2")).isEqualTo("Added to the favourite");
        
    }
    
    @Test
    public void findallfavoritemovietest() throws DataIsCurruptException, IOException, InterruptedException {
    	
    	List<WishList> list = new ArrayList();
    	list.add(wishlist);
        when(wishRepo.findByUsername("ankit")).thenReturn(list);
         List<MovieDto> listdto = wishlistService.findallfavouritemovies("ankit");
         assertThat(wishlistService.findallfavouritemovies("ankit")).isEqualTo(listdto);
       
    	
		
    }
    
    @Test
    public void findallfavoritemovietest_notfound() {
    	List<MovieDto> list = wishlistService.findallfavouritemovies("rohit");
    	assertThat(list.isEmpty()).isTrue();
		
    }
    
    
    @Test
    public void testDeleteById_Success() throws MovieNotFoundException {
        // Arrange
        Long entityId = 1L;

        Mockito.verify(wishRepo, Mockito.times(1)).deleteById(entityId);
        
        // Act
        wishlistService.removefromfavourite(entityId);

        // Assert
       }

    @Test
    public void testDeleteById_EntityNotFound() {
        // Arrange
        Long nonExistingEntityId = 999L;

        // Mock repository behavior for non-existing entity
        Mockito.doThrow(MovieNotFoundException.class)
               .when(wishRepo)
               .deleteById(nonExistingEntityId);

        // Act and Assert
       }

   
	
}
