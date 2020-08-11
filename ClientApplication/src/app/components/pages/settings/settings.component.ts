import { Component, OnInit } from '@angular/core';
import { HttpClientService } from 'src/app/services/httpClient/http-client.service';
import { User } from 'src/app/models/user';
import { AuthenticationService } from 'src/app/services/auth/authentication.service';
import { NotifierService } from 'angular-notifier';

@Component({
  selector: 'app-settings',
  templateUrl: './settings.component.html',
  styleUrls: ['./settings.component.css']
})
export class SettingsComponent implements OnInit {

  notifier: NotifierService;
  show;
  username = sessionStorage.getItem('username');
  newPassword: string = "";
  checkNewPassword: string = "";

  constructor(private http: HttpClientService, private authenticationService: AuthenticationService, private notifierService: NotifierService) {
    this.notifier = notifierService;
  }

  ngOnInit(): void {
  }

  public showNotification(type: string, message: string): void {
    this.notifier.notify(type, message);
  }

  changePassword() {
    if (this.newPassword === this.checkNewPassword) {
      const user: User = ({
        username: this.authenticationService.currentUserUsername,
        password: this.newPassword,
        role: null,
        email: null
      });
      this.http.updatePassword(user).subscribe(u => {
        this.showNotification('success', 'Pomyślnie zaktualizowano hasło');
        this.show = !this.show;
      },
        error => {
          this.showNotification('error', error);
        })
    }
    else {
      this.showNotification('error', 'Hasła nie są identyczne');
    }
    this.newPassword = "";
    this.checkNewPassword = "";
  }
}
