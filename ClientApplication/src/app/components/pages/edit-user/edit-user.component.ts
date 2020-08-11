import { Component, OnInit, Input } from '@angular/core';
import { User } from 'src/app/models/user';
import { HttpClientService } from 'src/app/services/httpClient/http-client.service';
import { NotifierService } from 'angular-notifier';
import { Router, ActivatedRoute } from '@angular/router';
import { AuthenticationService } from 'src/app/services/auth/authentication.service';

@Component({
  selector: 'app-edit-user',
  templateUrl: './edit-user.component.html',
  styleUrls: ['./edit-user.component.css']
})
export class EditUserComponent implements OnInit {

  username: string = "";
  password: string = "";
  description: string = "";
  email: string = "";
  passwordField: string = "**********";
  updateUsername: string;
  updateDescription: string;
  updateEmail: string = "";
  role: string;
  user: User;
  notifier: NotifierService;
  id: string;
  roles: Array<String> = [];

  constructor(private http: HttpClientService, notifierService: NotifierService,
    private route: ActivatedRoute, private router: Router, 
    private authService: AuthenticationService) {
    this.notifier = notifierService;
    this.id = this.route.snapshot.params.id;
    this.getUser(parseInt(this.id));
  }

  ngOnInit(): void {
  }

  public showNotification(type: string, message: string): void {
    this.notifier.notify(type, message);
  }

  getUser(id: number) {
    this.http.getUser(id).subscribe(res => {
      this.user = res;
      this.username = res.username;
      this.description = res.description;
      this.email = res.email;
      this.role = res.role;
      this.roles = [];
      this.roles.push(this.role, "admin", "user");
    },
      error => {
        this.showNotification('error', error);
        this.router.navigateByUrl('/admin-settings');
      });
  }

  updateUser(id: number) {
    this.user = ({
      id: id,
      username: this.updateUsername,
      password: this.password,
      role: this.role,
      description: this.updateDescription,
      email: this.updateEmail
    });

    this.http.updateUser(this.user).subscribe(u => {
      if (this.username === sessionStorage.getItem('username')) {
        this.showNotification('success', 'Udało się zaktualizować dane, zaloguj się ponownie');
        this.authService.logOut();
        this.router.navigateByUrl('/login');
      } else {
      this.getUser(id);
      this.showNotification('success', 'Udało się zaktualizować dane');
      this.updateUsername = "";
      this.updateDescription = "";
      this.updateEmail = "";
      }
    },
      error => {
        this.showNotification('error', error);
      });

    this.password = "";

  }

  deleteUser(id: number) {
    this.http.deleteUser(id).subscribe(() => {
      this.showNotification('success', 'Pomyślnie usunięto użytkownika');
    },
      error => {
        this.showNotification('error', error);
      });
    this.router.navigateByUrl('/admin-settings');
  }
}
