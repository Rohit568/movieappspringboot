import { Component, OnInit } from '@angular/core';
import { TokenService } from '../token.service';
import { Router } from '@angular/router';

@Component({
  selector: 'app-header',
  templateUrl: './header.component.html',
  styleUrls: ['./header.component.css']
})
export class HeaderComponent implements OnInit {
 

  constructor(private tokenService: TokenService, private router : Router) { }

  ngOnInit(): void {

    
  }

  logout(){
     localStorage.removeItem("auth-token");
     localStorage.removeItem("auth-user");
     this.router.navigate(["login"]);
  }

}
