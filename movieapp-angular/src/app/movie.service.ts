import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Router } from '@angular/router';
import { Observable, map } from 'rxjs';
import { Movie } from 'src/model/movie';
import { Token } from 'src/model/token';
import { User } from 'src/model/user';
import { TokenService } from './token.service';
import { WishListMovies } from 'src/model/wishlistMovies';
import { FavouriteMovie } from 'src/model/favouriteMovie';
import { ResponseMessage } from 'src/model/response';
import { LoginRequest } from 'src/model/loginRequest';

const TOKEN_KEY = 'auth-token';
const USER_KEY = 'auth-user';

@Injectable({
  providedIn: 'root'
})
export class MovieService {
  
  
  
  apiUrl:string = "http://localhost:8321/";
  // apiUrl1:string = "http://localhost:8081/";
  // apiUrl2:string = "http://localhost:8082/";
  // apiUrl3:string = "http://localhost:8083/";
  // apiUrl5:string = "http://localhost:8084/";
  constructor(private http : HttpClient, private router:Router, private tokenService:TokenService) { }

  getAll():Observable<Movie[]>
  {
    let response =this.http.get<Movie[]>(this.apiUrl + "movie/api");
    return response;
   }

   getFavourite():Observable<FavouriteMovie[]>{
     let user = this.tokenService.getUser();
     let response =this.http.get<FavouriteMovie[]>(this.apiUrl + "wishlist/favourites/"+user);
    return response;
   }
  login(username: string, password: string){
    return this.http.post<Token>(this.apiUrl+"auth/token", { username, password })
        .pipe(map(token => {
            // store user details and jwt token in local storage to keep user logged in between page refreshes
           this.tokenService.saveUser(username);
           this.tokenService.saveToken(token.token);
        }));
}

register(user: User) : Observable<User>{
    return this.http.post<User>(this.apiUrl + "user/register" , user)
}


findfavouritebyid(id : number):Observable<WishListMovies>{
  let response =this.http.get<WishListMovies>(this.apiUrl + "wishlist/favourite/"+id);
  return response;
}

deletefavouritebyid(id : number){
  let response  = this.http.delete<WishListMovies>(this.apiUrl+"wishlist/remove/"+id);
  return response;
}

getmoviebyid(id : string){
  let response = this.http.get<Movie>(this.apiUrl +"movie/" +id);
  return response;
}

addtofavourite(id: string) {
  let  user = this.tokenService.getUser();
  let response = this.http.get<ResponseMessage>(this.apiUrl+"wishlist/"+user+"/"+id);
  return response;
}
updatepassword(password: string) {
  
  let user = this.tokenService.getUser();
  const l = new LoginRequest(""+user, password);
  let response = this.http.put<User>(this.apiUrl+"user/update",l);
  return response;

}
getuserprofile() {
  let  user = this.tokenService.getUser();
  let response = this.http.get<User>(this.apiUrl+"user/detail/"+user);
  return response;
}
 
}
