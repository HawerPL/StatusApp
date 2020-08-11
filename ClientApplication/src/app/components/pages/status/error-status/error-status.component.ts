import { Component, OnInit } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { HttpClientApplicationsService } from 'src/app/services/httpClient/http-client-applications.service';
import { NotifierService } from 'angular-notifier';
import { Application } from 'src/app/models/application';

@Component({
  selector: 'app-error-status',
  templateUrl: './error-status.component.html',
  styleUrls: ['./error-status.component.css']
})
export class ErrorStatusComponent{

  notifier: NotifierService;
  guid_app: string;
  application: Application = {
    name: "",
    description: "",
    status: false,
    guid_production: "",
    guid_app: ""
  };

  constructor(private http: HttpClientApplicationsService, private notifierService: NotifierService,
    private route: ActivatedRoute, private router: Router) {
    this.notifier = notifierService;
    this.guid_app = this.route.snapshot.params.guid_app;
    this.getApplication();
  }

  public showNotification(type: string, message: string): void {
    this.notifier.notify(type, message);
  }

  getApplication() {
    this.http.getApplication(this.guid_app).subscribe(res => {
      this.application = res;
    },
      error => {
        this.showNotification('error', error);
      })
  }
}
