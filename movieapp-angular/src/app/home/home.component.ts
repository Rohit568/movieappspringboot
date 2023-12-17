import { Component, OnInit } from '@angular/core';
import { Movie } from 'src/model/movie';
import { MovieService } from '../movie.service';
import { Router } from '@angular/router';import { FavouriteMovie } from 'src/model/favouriteMovie';
;

@Component({
  selector: 'app-home',
  templateUrl: './home.component.html',
  styleUrls: ['./home.component.css']
})
export class HomeComponent implements OnInit {

  favouriteFlag: boolean = false;
  allFlag: boolean = true;
  movies: Movie[] = [];
  wishList: FavouriteMovie[] = [];
  flag_favourite : boolean = false;

  constructor(private movieService: MovieService, private router: Router) { }

  ngOnInit(): void {
    this.flag_favourite =false;
     this.showfavourites();
  }


  showall() {
    this.favouriteFlag = false;
    this.allFlag = true;

    this.movieService.getAll().subscribe(data => {
      this.movies = data;
    },
      error => {
        this.router.navigateByUrl('login');
      })

  }
  showfavourites() {
    this.allFlag = false;
    this.favouriteFlag = true;
    this.movieService.getFavourite().subscribe(data => {
     // console.log(data);
      this.wishList = data;
      if(this.wishList.length == 0)
      {
        this.flag_favourite = true;
      }
    },
      error => {
        this.router.navigateByUrl('login');
      })
  }


  gotomoviedetail(id:number)
  {
    this.router.navigate(['/movie', { id:id }]);
  }
  


}
