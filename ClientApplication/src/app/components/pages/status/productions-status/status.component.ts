import { Component, OnInit } from '@angular/core';
import { Test } from 'src/app/models/test';
import { AuthenticationService } from 'src/app/services/auth/authentication.service';
import { NotifierService } from 'angular-notifier';
import { HttpClientProductionsService } from 'src/app/services/httpClient/http-client-productions.service';
import { Production } from 'src/app/models/production';


@Component({
  selector: 'app-status',
  templateUrl: './status.component.html',
  styleUrls: ['./status.component.css']
})
export class StatusComponent implements OnInit {

  productionsArray: Array<Production>;
  name: string = "";
  description: string;
  production: Production;
  notifier: NotifierService;

  constructor(private auth: AuthenticationService, private notifierService: NotifierService,
    private http: HttpClientProductionsService) {
      this.notifier = notifierService;
     }

  testArray: Array<Test>;
  
  ngOnInit(): void {
    this.getProductions();
  }

  public showNotification(type: string, message: string): void {
    this.notifier.notify(type, message);
  }

  getProductions() {
    this.http.getProductions().subscribe(productions => {
      this.productionsArray = productions;
    },
      error => {
        this.showNotification('error', error);
      });
  }
  

}
