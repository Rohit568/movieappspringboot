import { Injectable } from '@angular/core';
import {
  HttpRequest,
  HttpHandler,
  HttpEvent,
  HttpInterceptor
} from '@angular/common/http';
import { Observable } from 'rxjs';
import { TokenService } from './token.service';

const TOKEN_HEADER_KEY = 'Authorization';  

@Injectable()
export class AuthInterceptor implements HttpInterceptor {

  constructor(private token : TokenService) {}
 
  intercept(request: HttpRequest<unknown>, next: HttpHandler): Observable<HttpEvent<unknown>> {
    

    if(request.url == 'http://localhost:8321/user/register')
    return next.handle(request);

    let authReq = request;
    console.log(authReq);
    const token = this.token.getToken();
    if (token != null) {
      authReq = request.clone({ headers: request.headers.set(TOKEN_HEADER_KEY, 'Bearer ' + token) });

    }
    return next.handle(authReq);
   // return next.handle(request);
  }
}
