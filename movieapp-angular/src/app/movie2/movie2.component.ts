import { Component, OnInit } from '@angular/core';
import { MovieService } from '../movie.service';
import { ActivatedRoute, Router } from '@angular/router';
import { Movie } from 'src/model/movie';

@Component({
  selector: 'app-movie2',
  templateUrl: './movie2.component.html',
  styleUrls: ['./movie2.component.css']
})
export class Movie2Component implements OnInit {

  constructor(private movieService: MovieService, private route:ActivatedRoute, private router: Router) { }
  id!:string;
  movie!:Movie;
  ngOnInit(): void {
    this.route.params.subscribe(params => {
      const paramValue = params['id'];
      // Do something with the parameter value
        this.getmoviedetail(paramValue)
    });
  }
 


  getmoviedetail(id : string){
    this.movieService.getmoviebyid(id).subscribe(
      data =>{
         console.log(data);
          this.movie =data;
      },
      error =>{
        this.router.navigate(['login']);
      }
    )
  }

  addtofavourite(id:string){
    this.movieService.addtofavourite(id).subscribe(
      data =>{
        alert(data.msg);
      },
      error=>{

      }
    )
  }

}
