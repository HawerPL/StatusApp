import { Component, DoCheck } from '@angular/core';
import { AuthenticationService } from 'src/app/services/auth/authentication.service';
import { Router } from '@angular/router';
import { AESEncryptDecryptService } from 'src/app/services/AESEncryptDecryptService/aesencrypt-decrypt.service';

@Component({
  selector: 'app-navbar',
  templateUrl: './navbar.component.html',
  styleUrls: ['./navbar.component.css']
})
export class NavbarComponent implements DoCheck {

  isLogged: boolean;
  isAdmin: boolean;
  collapsed = true;


  constructor(private loginService: AuthenticationService, private router: Router, private aesEncryptDecryptService: AESEncryptDecryptService) { }

  ngDoCheck(): void {
    this.isLogged = this.loginService.isUserLoggedIn();
    if (this.isLogged) {
      if (this.aesEncryptDecryptService.decrypt(sessionStorage.getItem('role')) == 'admin') {
        this.isAdmin = true;
      }
      else {
        this.isAdmin = false;
      }
    }
  }

  toggleClicked(){
    this.collapsed = !this.collapsed;
  }

  logout() {
    this.loginService.logOut();
    window.location.reload();
  }

}
