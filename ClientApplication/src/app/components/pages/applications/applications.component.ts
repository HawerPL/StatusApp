import { Component, OnInit, Input } from '@angular/core';
import { Application } from 'src/app/models/application';
import { HttpClientApplicationsService } from 'src/app/services/httpClient/http-client-applications.service';
import { NotifierService } from 'angular-notifier';

@Component({
  selector: 'app-applications',
  templateUrl: './applications.component.html',
  styleUrls: ['./applications.component.css']
})
export class ApplicationsComponent implements OnInit {


  @Input() guid: string;
  applicationArray: Array<Application>;
  application: Application;
  guid_app: string;
  guid_production: string;
  name: string = "";
  description: string;
  notifier: NotifierService;

  constructor(private http: HttpClientApplicationsService, private notifierService: NotifierService) {
    this.notifier = notifierService;
  }

  ngOnInit(): void {
    this.getApplications();
  }

  public showNotification(type: string, message: string): void {
    this.notifier.notify(type, message);
  }

  getApplications() {
    this.http.getApplicationsByGuidProductions(this.guid).subscribe(res => {
      this.applicationArray = res;
    },
      error => {
        this.showNotification('error', error);
      });
  }

  addApplication() {
    this.application = ({
      guid_app: null,
      name: this.name,
      guid_production: this.guid,
      description: this.description,
    })
    this.http.addApplication(this.application).subscribe(() => {
      this.showNotification('success', 'Pomyślnie dodano aplikację');
      this.getApplications();
      this.name = "";
      this.description = "";
    },
      error => {
        this.showNotification('error', error);
      });

  }
}
