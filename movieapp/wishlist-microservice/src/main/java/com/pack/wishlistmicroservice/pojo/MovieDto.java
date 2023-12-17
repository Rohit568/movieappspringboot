package com.pack.wishlistmicroservice.pojo;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class MovieDto {
	
	private long id;
	private String rating;
	private long ranking;
	private String image;
	private String title;
	private long year;

}
