import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';

import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { LoginComponent } from './login/login.component';
import { RegisterComponent } from './register/register.component';
import { HeaderComponent } from './header/header.component';
import { HomeComponent } from './home/home.component';
import { HTTP_INTERCEPTORS, HttpClientModule } from '@angular/common/http';
import { ReactiveFormsModule } from '@angular/forms';
import { AuthInterceptor } from './auth.interceptor';
import { MovieDetailComponent } from './movie-detail/movie-detail.component';
import { Movie2Component } from './movie2/movie2.component';
import { ProfileComponent } from './profile/profile.component';
import { Header2Component } from './header2/header2.component';
import { HttpClientTestingModule } from '@angular/common/http/testing';

@NgModule({
  declarations: [
    AppComponent,
    LoginComponent,
    RegisterComponent,
    HeaderComponent,
    HomeComponent,
    MovieDetailComponent,
    Movie2Component,
    ProfileComponent,
    Header2Component
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    HttpClientModule,
    ReactiveFormsModule
  ],
  providers: [
    {
      provide: HTTP_INTERCEPTORS,
      useClass: AuthInterceptor,
      multi: true
  }
  ],
  bootstrap: [AppComponent]
})
export class AppModule { }
