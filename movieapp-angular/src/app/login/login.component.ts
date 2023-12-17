import { Component, OnInit } from '@angular/core';
import { FormGroup, FormBuilder, Validators } from '@angular/forms';
import { MovieService } from '../movie.service';
import { Router } from '@angular/router';

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css']
})
export class LoginComponent implements OnInit {

  loginForm !: FormGroup
  submitted:boolean = false;
  invalid :boolean = false;
  error_msg!:string;
  error_flag :boolean = false;
  constructor(private movieServie : MovieService, private formBuilder : FormBuilder ,
    private router:Router) {
    
   }



  ngOnInit(): void {
    this.invalid = false;
    this.loginForm = this.formBuilder.group({
      username: ["",[Validators.required, Validators.minLength(4), Validators.pattern(/^[a-zA-Z]+$/)]],
      password: [
        "",
        [
          Validators.required,
          Validators.pattern('^[a-z0-9A-Z]{6,}$')
       ]
      ]
    });
  }

  get f()
{
    return this.loginForm.controls;
}
  onSubmit(){
    if(!this.loginForm.valid)
    this.invalid = true;
    if(this.loginForm.valid){
    this.movieServie.login(this.loginForm.value.username, this.loginForm.value.password).subscribe(
      data =>{
        this.router.navigateByUrl("home");
        console.log("succesfull login");
    },
    error =>{
        this.error_flag = true;
         this.error_msg = "wrong username or password";

         setTimeout(() => {
          this.error_flag = false;
         }, 5000);

      
    });
  }
  }

}
