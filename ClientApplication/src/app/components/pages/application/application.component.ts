import { Component, OnInit } from '@angular/core';
import { Application } from 'src/app/models/application';
import { NotifierService } from 'angular-notifier';
import { HttpClientApplicationsService } from 'src/app/services/httpClient/http-client-applications.service';
import { ActivatedRoute, Router } from '@angular/router';
import { HttpClientProductionsService } from 'src/app/services/httpClient/http-client-productions.service';

@Component({
  selector: 'app-application',
  templateUrl: './application.component.html',
  styleUrls: ['./application.component.css']
})
export class ApplicationComponent implements OnInit {


  notifier: NotifierService;
  application: Application;
  guidProductionsArray: Array<String> = [];
  guid_app: string;
  guid_production: string = "";
  name: string;
  description: string;
  updateName: string = "";
  updateDescription: string = "";

  constructor(private http: HttpClientApplicationsService, private notifierService: NotifierService,
    private route: ActivatedRoute, private httpProductions: HttpClientProductionsService,
    private router: Router) {
    this.notifier = notifierService;
    
  }

  ngOnInit(): void {
    this.guid_app = this.route.snapshot.params.guid_app;
    this.guid_production = this.route.snapshot.params.guid;
    this.getApplication(this.guid_app);
    
  }

  public showNotification(type: string, message: string): void {
    this.notifier.notify(type, message);
  }

  getAllGuidProductions() {
    this.httpProductions.getProductions().subscribe(res => {
      this.guidProductionsArray.push(this.application.guid_production);
      res.forEach(element => {
        this.guidProductionsArray.push(element.guid);
      })
    },
      error => {
        this.showNotification('error', error);
      });
  }

  getApplication(guid: string) {
    this.http.getApplication(guid).subscribe(res => {
      this.application = res;
      this.guid_app = res.guid_app;
      this.guid_production = res.guid_production;
      this.name = res.name;
      this.description = res.description;
      this.getAllGuidProductions();
    },
      error => {
        this.showNotification('error', error);
      }
    );
  }

  updateApplication(guid_app: string) {
    this.application = ({
      guid_app: guid_app,
      guid_production: this.guid_production,
      name: this.updateName,
      description: this.updateDescription
    })
    this.http.updateApplication(this.application).subscribe(() => {
      this.showNotification('success', "Pomyślnie zaktualizowano aplikację");
      this.getApplication(guid_app);
    },
      error => {
        this.showNotification('error', error);
      });
    this.updateName = "";
    this.updateDescription = "";
  }

  deleteApplication(guid_app: string) {
    this.http.deleteApplication(guid_app).subscribe(() => {
      this.showNotification('success', 'Pomyślnie usunięto aplikację');
      this.router.navigateByUrl('/productions/' + this.guid_production);
    },
      error => {
        this.showNotification('error', error);
      });
    this.router.navigateByUrl('/productions/' + this.guid_production);
  }
}
