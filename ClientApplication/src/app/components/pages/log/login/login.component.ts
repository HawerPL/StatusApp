import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { AuthenticationService } from 'src/app/services/auth/authentication.service';
import { AESEncryptDecryptService } from 'src/app/services/AESEncryptDecryptService/aesencrypt-decrypt.service';

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css']
})
export class LoginComponent implements OnInit {

  username: string;
  password: string;
  invalidLogin = false;
  
  constructor(private router: Router, private authenticationService: AuthenticationService, private aesEncryptDecryptService: AESEncryptDecryptService) { }

  ngOnInit(): void {
    if(this.authenticationService.isUserLoggedIn()){
      this.router.navigate(['home']);
    }
  }

  login(){
    this.authenticationService.authenticate(this.username, this.password).subscribe(
      data =>{
        this.authenticationService.role().subscribe(
          data =>{
            sessionStorage.setItem('role', this.aesEncryptDecryptService.encrypt(data.role));
          });
        this.router.navigate(['home']);
        this.invalidLogin = false;
      },
      error =>{
        this.invalidLogin = true;
        
      }
    );
  }
  
}
