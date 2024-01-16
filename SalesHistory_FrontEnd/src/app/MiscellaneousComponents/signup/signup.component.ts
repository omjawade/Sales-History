import { Component } from '@angular/core';
import { Router } from '@angular/router';
import { AuthService } from 'src/app/services/auth.service';
@Component({
  selector: 'app-signup',
  templateUrl: './signup.component.html',
  styleUrls: ['./signup.component.css']
})
export class SignupComponent {
  form: any = {
    username: null,
    email: null,
    password: null
  };
  isSuccessful = false;
  isSignUpFailed = false;
  errorMessage = '';
  constructor(private authService: AuthService,private router:Router) { }
  ngOnInit(): void {
  }
  onSubmit(): void {
    const { username, email, password } = this.form;
    this.authService.register(username, email, password).subscribe({
      next:(data:any)=>{
        console.log(data);
        this.isSuccessful = true;
        this.isSignUpFailed = false;
        alert("User Registered Successfully")
        this.router.navigateByUrl("home")
      },error:(err)=>{
        alert("Username already exists")
        this.errorMessage = err.message;
        this.isSignUpFailed = true;
      }
    }
    );
  }
}