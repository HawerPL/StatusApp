import { Component, OnInit } from '@angular/core';
import { NotifierService } from 'angular-notifier';
import { HttpClientApplicationsService } from 'src/app/services/httpClient/http-client-applications.service';
import { Application } from 'src/app/models/application';
import { Router, ActivatedRoute } from '@angular/router';

@Component({
  selector: 'app-applications-status',
  templateUrl: './applications-status.component.html',
  styleUrls: ['./applications-status.component.css']
})
export class ApplicationsStatusComponent {

  applicationArray: Array<Application>;
  application: Application;
  guid_app: string;
  guid_production: string;
  notifier: NotifierService;

  constructor(private http: HttpClientApplicationsService, private notifierService: NotifierService,
    private route: ActivatedRoute, private router: Router) {
    this.notifier = notifierService;
    this.guid_production = this.route.snapshot.params.guid;
    this.getApplications();
  }

  public showNotification(type: string, message: string): void {
    this.notifier.notify(type, message);
  }

  getApplications() {
    this.http.getApplicationsByGuidProductions(this.guid_production).subscribe(res => {
      this.applicationArray = res;
    },
      error => {
        this.showNotification('error', error);
      });
  }
}
