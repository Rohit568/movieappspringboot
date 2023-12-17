import { Component, OnInit } from '@angular/core';
import { MovieService } from '../movie.service';
import { ActivatedRoute, Router } from '@angular/router';
import { WishListMovies } from 'src/model/wishlistMovies';

@Component({
  selector: 'app-movie-detail',
  templateUrl: './movie-detail.component.html',
  styleUrls: ['./movie-detail.component.css']
})
export class MovieDetailComponent implements OnInit {

  constructor(private movieService: MovieService, private route:ActivatedRoute, private router: Router) { }
  id!:string;
  wishList!:WishListMovies;
  ngOnInit(): void {
    this.route.params.subscribe(params => {
      const paramValue = params['id'];
      // Do something with the parameter value
        this.getmoviedetail(paramValue)
    });
  }

  getmoviedetail(id:number){
      this.movieService.findfavouritebyid(id).subscribe(
        data =>{
          
            this.wishList =data;
            
        },
        error =>{
           this.router.navigate(["login"]);
        }
      )
  }

  removefromfavourite(id: number){
    this.movieService.deletefavouritebyid(id).subscribe(
      data=>{
         alert("deleted successfully!");
    })
  }

}
