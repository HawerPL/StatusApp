import { Component, OnInit } from '@angular/core';
import { Production } from 'src/app/models/production';
import { HttpClientProductionsService } from 'src/app/services/httpClient/http-client-productions.service';
import { AuthenticationService } from 'src/app/services/auth/authentication.service';
import { HttpClientApplicationsService } from 'src/app/services/httpClient/http-client-applications.service';
import { NotifierService } from 'angular-notifier';

@Component({
  selector: 'app-productions',
  templateUrl: './productions.component.html',
  styleUrls: ['./productions.component.css']
})
export class ProductionsComponent implements OnInit {

  productionsArray: Array<Production>;
  name: string = "";
  description: string;
  production: Production;
  notifier: NotifierService;

  constructor(private http: HttpClientProductionsService, private auth: AuthenticationService, private httpApps: HttpClientApplicationsService, private notifierService: NotifierService) {
    this.notifier = notifierService;
  }

  ngOnInit(): void {
    this.getProductions();
  }

  public showNotification(type: string, message: string): void {
    this.notifier.notify(type, message);
  }

  addProduction() {
    this.production = ({
      guid: null,
      name: this.name,
      description: this.description
    });
    this.http.addProduction(this.production).subscribe(() => {
      this.showNotification('success', 'Pomyślnie dodano produkcję');
      this.getProductions();
      this.name = "";
      this.description = "";
    },
    error =>{
      this.showNotification('error', error);
    });

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
