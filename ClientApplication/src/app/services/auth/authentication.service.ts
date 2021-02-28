import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { map } from 'rxjs/operators';
import { environment } from 'src/environments/environment';
import { NotifierService } from 'angular-notifier';

@Injectable({
  providedIn: 'root'
})
export class AuthenticationService {

  currentUserUsername: string;
  currentUserRole: string;
  notifier: NotifierService;

  constructor(private http: HttpClient, notifierService: NotifierService) {
    this.notifier = notifierService;
   }

  authenticate(username, password) {
    return this.http.post<any>(`${environment.api_url}/authenticate`, { username, password }).pipe(
      map(
        userData => {
          sessionStorage.setItem('username', username);
          this.currentUserUsername = username;
          let tokenStr = "Bearer " + userData.token;
          sessionStorage.setItem('token', tokenStr);
          return userData;
        },
        error =>{
          
        }
      )
    );
  }

  public showNotification(type: string, message: string): void {
    this.notifier.notify(type, message);
  }

  role() {
    if (this.isUserLoggedIn) {
      return this.http.post<any>(`${environment.api_url}/authenticateRole`, this.currentUserUsername);
    }
  }

  isUserLoggedIn() {
    if (sessionStorage.getItem('token') == null) {
      return false;
    }
    else {
      return true;
    }
  }

  logOut() {
    sessionStorage.removeItem('username');
    sessionStorage.removeItem('token');
    sessionStorage.removeItem('role');
  }
}
