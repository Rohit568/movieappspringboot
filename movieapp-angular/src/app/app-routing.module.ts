import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { RegisterComponent } from './register/register.component';
import { HomeComponent } from './home/home.component';
import { LoginComponent } from './login/login.component';
import { MovieDetailComponent } from './movie-detail/movie-detail.component';
import { Movie2Component } from './movie2/movie2.component';
import { AuthGuard } from './myauthgaurd';
import { ProfileComponent } from './profile/profile.component';

const routes: Routes = [
  { path: '', redirectTo: 'login', pathMatch: 'full' },
  { path :'register', component:RegisterComponent},
  { path : 'home', component:HomeComponent,canActivate: [AuthGuard]},
  { path : 'login', component: LoginComponent},
  { path : 'movie/:id', component : MovieDetailComponent,canActivate: [AuthGuard]},
  { path : 'movie2/:id', component:Movie2Component,canActivate: [AuthGuard]},
  {
    path : 'profile', component:ProfileComponent, canActivate:[AuthGuard]
  }
  
]
;

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
