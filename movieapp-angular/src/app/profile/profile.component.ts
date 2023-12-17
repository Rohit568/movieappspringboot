import { Component, OnInit } from '@angular/core';
import { MovieService } from '../movie.service';
import { User } from 'src/model/user';

@Component({
  selector: 'app-profile',
  templateUrl: './profile.component.html',
  styleUrls: ['./profile.component.css']
})
export class ProfileComponent implements OnInit {

  userProfile!: User;
  flag_update :boolean = false;
  update_msg : string = "";
  constructor(private movieService:MovieService) { }

  ngOnInit(): void {
    this.flag_update = false;
    this.getProfile();
  }

  getProfile(){
    this.movieService.getuserprofile().subscribe(
      data =>{
           this.userProfile = data;
      },
      error=>{

      }
    )
  }

  updatepassword(password:string){
    this.movieService.updatepassword(password).subscribe(
      data=>{
         this.flag_update = true;
         this.update_msg = "Updated Succesfully";
      },
      error=>{

      }

     );
      
    
  }

}
