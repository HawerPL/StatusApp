import { Component, OnInit, Input } from '@angular/core';
import { User } from 'src/app/models/user';
import { HttpClientService } from 'src/app/services/httpClient/http-client.service';
import { NotifierService } from 'angular-notifier';

@Component({
  selector: 'app-admin-settings',
  templateUrl: './admin-settings.component.html',
  styleUrls: ['./admin-settings.component.css']
})
export class AdminSettingsComponent implements OnInit {

  @Input() userId = null;
  userArray: Array<User> = null;
  username: string = "";
  description: string = "";
  password: string = "";
  email: string = "";
  role: string = "user";
  user: User;
  private notifier: NotifierService;


  options = [
    { name: "user", value: "user" },
    { name: "admin", value: "admin" }
  ]

  constructor(private http: HttpClientService, notifier: NotifierService) {
    this.notifier = notifier;
   }

  ngOnInit(): void {
    this.getUsers();
  }

  public showNotification( type: string, message: string ): void {
		this.notifier.notify( type, message );
  }
  
  getUsers() {
    this.http.getUsers().subscribe(users => {
      this.userArray = users;
    });
  }

  addUser() {
    this.user = ({
      username: this.username,
      password: this.password,
      description: this.description,
      role: this.role,
      email: this.email
    });
    if(this.user.password.length < 6){
      this.showNotification('error', 'Hasło musi składać się z minimum 6 znaków');
    }
    else{
      this.http.addUser(this.user).subscribe(() => {
        this.getUsers();
        this.showNotification('success', 'Udało się dodać użytkownika');
        this.username = "";
        this.password = "";
        this.description = "";
        this.email = "";
        this.role = null;
      },
      error =>{
        this.showNotification('error', error);
      }
      );
    }
  }
}
