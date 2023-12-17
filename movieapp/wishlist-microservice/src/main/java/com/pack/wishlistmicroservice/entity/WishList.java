package com.pack.wishlistmicroservice.entity;





import java.time.LocalDate;

import org.springframework.format.annotation.DateTimeFormat;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
@Builder
@Table(name = "wishlist")
public class WishList {
 
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long id;
    private long rankGiven;
	private String movieId;
	private String username;
    private String titleName;
    private String description;
    private String imageName;
    private String bigImageName;
    private String ratingGiven;
    @DateTimeFormat(pattern="dd/MM/yyyy")
    private LocalDate dateAdded;
    private int yearAdded;
}
