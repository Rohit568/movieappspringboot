package com.pack.wishlistmicroservice.pojo;



import org.springframework.stereotype.Component;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Component
public class MoviePojo {

	private String id;
    private long rank;
    private String title;
    private String description;
    private String image;
    private String rating;
    private String big_image;
    private int year;
    
}
