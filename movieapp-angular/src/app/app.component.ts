import { Component } from '@angular/core';
import { TokenService } from './token.service';

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.css']
})
export class AppComponent {
  isLoggedIn = false;
  showAdminBoard = false;
  showModeratorBoard = false;
  username!: string;

  constructor(private tokenService: TokenService) { }

  ngOnInit(): void {
    this.isLoggedIn = !!this.tokenService.getToken();

    if (this.isLoggedIn) {
      this.username!= this.tokenService.getUser();
      
    }
  }

  logout(): void {
    this.tokenService.signOut();
    window.location.reload();
  }}
