import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { User } from 'src/model/user';
import { MovieService } from '../movie.service';
import { ActivatedRoute, Router } from '@angular/router';

@Component({
  selector: 'app-register',
  templateUrl: './register.component.html',
  styleUrls: ['./register.component.css']
})
export class RegisterComponent implements OnInit {

  registerForm!: FormGroup;
  iserror:boolean = false;
  error_msg:string=""

  constructor(private movieService: MovieService,  private formBuilder: FormBuilder,private route: ActivatedRoute, private router:Router) {}

  ngOnInit() {
    this.createForm();
  }

  createForm() {
    this.registerForm = this.formBuilder.group({
       username: ['', [Validators.required ,Validators.minLength(4), Validators.pattern(/^[a-zA-Z]+$/)]],
       email:['', [Validators.required,Validators.email]],
       password :['', 
       [
        Validators.required,
        Validators.pattern('^[a-z0-9A-Z]{6,}$')
          ]
      ]

      
    
  });
  }

  onSubmit() {
    this.iserror = false;
    if (this.registerForm.valid) {
      const user: User = this.registerForm.value;
      this.movieService.register(user).subscribe(data=>{
            console.log(data);
            this.router.navigateByUrl('login');
      },
      error =>{
           this.error_msg =error;
           this.iserror = true;
      })
    
    } else {
      // Form is invalid, mark fields as touched to display error messages
      this.markFormGroupTouched(this.registerForm);
    }
  }
  markFormGroupTouched(registrationForm: FormGroup<any>) {
    throw new Error('Method not implemented.');
  }

}
