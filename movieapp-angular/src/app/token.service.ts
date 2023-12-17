import { useAnimation } from '@angular/animations';
import { Injectable } from '@angular/core';
import { Router } from '@angular/router';

const TOKEN_KEY = 'auth-token';
const USER_KEY = 'auth-user';
@Injectable({
  providedIn: 'root'
})
export class TokenService {

  constructor(private router:Router) { }

  signOut(): void {
    localStorage.removeItem(TOKEN_KEY);
    localStorage.removeItem(USER_KEY);
    this.router.navigateByUrl("login");
  }

  public saveToken(token: string): void {
    localStorage.removeItem(TOKEN_KEY);
    localStorage.setItem(TOKEN_KEY, token);
  }

  public getToken(): string |null {
    return localStorage.getItem(TOKEN_KEY);
  }

  public saveUser(user:string): void {
    localStorage.removeItem(USER_KEY);
    localStorage.setItem(USER_KEY, user);
  }

  public getUser(): string |null {
    return localStorage.getItem(USER_KEY);
  }
}
