package com.pack.wishlistmicroservice.repository;



import static org.assertj.core.api.Assertions.assertThat;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.pack.wishlistmicroservice.WishlistMicroserviceApplication;
import com.pack.wishlistmicroservice.entity.WishList;
import com.pack.wishlistmicroservice.repository.WishListREpository;

import jakarta.transaction.Transactional;


@RunWith(SpringRunner.class) 
@SpringBootTest(classes=WishlistMicroserviceApplication.class)
public class WishListREpositoryTest {
	
	@Autowired
    private WishListREpository wishRepo;
	WishList wishList;
	
	@Before
	public void setup() {
		WishList s = WishList.builder()
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
		           
       wishList= wishRepo.save(s);
	}
	
	@After
	public void unsetup()
	{
		wishList = null;
		wishRepo.deleteAll();
	}
	//success
	@Test
	public void testfindbyusernameandmovieid() {
		
		WishList movie = wishRepo.findByUsernameAndMovieId("ankit", "top2").orElse(null);
		assertThat(movie.getTitleName()).isEqualTo(wishList.getTitleName());
		assertThat(movie.getRankGiven()).isEqualTo(wishList.getRankGiven());
		
	}

	@Test
	public void testfindbyusernameandmovieid_notfound() {
		
		Optional<WishList> movie = wishRepo.findByUsernameAndMovieId("rohit", "top2");
		assertThat(movie.isEmpty()).isTrue();
		
		
	}
	
	@Test
	public void testfindbyusername() {
		
		List<WishList> movieList = wishRepo.findByUsername("ankit");
		assertThat(movieList.get(0).getTitleName()).isEqualTo(wishList.getTitleName());
		assertThat(movieList.get(0).getRankGiven()).isEqualTo(wishList.getRankGiven());
		
	}

	@Test
	public void testfindbyusername_notfound() {
		
		List<WishList> movieList = wishRepo.findByUsername("rohit");
	    assertThat(movieList.isEmpty()).isTrue();
		
		
	}
}
