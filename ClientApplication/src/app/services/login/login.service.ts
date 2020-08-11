import { Injectable } from '@angular/core';
import { Router } from '@angular/router';
import { AuthenticationService } from '../auth/authentication.service';
import { NotifierService } from 'angular-notifier';

@Injectable({
  providedIn: 'root'
})
export class LoginService {

  username = '';
  password = '';
  invalidLogin = false;
  notifier: NotifierService;

  constructor(private router: Router, private loginService: AuthenticationService,
    notifierService: NotifierService) {
    this.notifier = notifierService;
  }

  public showNotification(type: string, message: string): void {
    this.notifier.notify(type, message);
  }

  checkLogin(username, password) {
    this.loginService.authenticate(username, password).subscribe(
      data => {
        this.router.navigate(['/home']);
        this.invalidLogin = false;
      },
      error => {
        this.invalidLogin = true;
        this.showNotification('error', error);
      }
    )
  }

}
