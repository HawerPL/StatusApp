import { Component, OnInit, Input, Output, EventEmitter } from '@angular/core';
import { Production } from 'src/app/models/production';
import { NotifierService } from 'angular-notifier';
import { AuthenticationService } from 'src/app/services/auth/authentication.service';
import { HttpClientProductionsService } from 'src/app/services/httpClient/http-client-productions.service';
import { HttpClientApplicationsService } from 'src/app/services/httpClient/http-client-applications.service';
import { ActivatedRoute, Router } from '@angular/router';


@Component({
  selector: 'app-production',
  templateUrl: './production.component.html',
  styleUrls: ['./production.component.css']
})
export class ProductionComponent implements OnInit {

  guid_p: string;
  name: string;
  description: string;
  updateName: string = "";
  updateDescription: string = "";
  production: Production;
  notifier: NotifierService;
  show: any;

  constructor(private http: HttpClientProductionsService, private auth: AuthenticationService,
    private httpApps: HttpClientApplicationsService, private notifierService: NotifierService,
    private route: ActivatedRoute, private router: Router) {
    this.notifier = notifierService;
    this.guid_p = this.route.snapshot.params.guid;
    this.getProduction(this.guid_p);
  }

  ngOnInit(): void {
  }

  public showNotification(type: string, message: string): void {
    this.notifier.notify(type, message);
  }

  getProduction(guid_p: string) {
    this.http.getProduction(guid_p).subscribe(res => {
      this.production = res;
      this.name = res.name;
      this.description = res.description;
    },
    error => {
      this.showNotification('error', error);
      this.router.navigateByUrl('/productions');
    });
  }

  updateProduction(guid: string) {
    this.production = ({
      guid: guid,
      name: this.updateName,
      description: this.updateDescription
    });
    this.http.updateProduction(this.production).subscribe(() => {
      this.showNotification('success', 'Pomyślnie zaktualizowano produkcję');
      this.getProduction(guid);
    },
      error => {
        this.showNotification('error', error);
      });
      this.updateName = "";
      this.updateDescription = "";
  }

  deleteProduction(guid: string) {
    this.http.deleteProduction(guid).subscribe(() => {
      this.showNotification('success', 'Pomyślnie usunięto produkcję');
      this.router.navigateByUrl('/productions');
    },
      error => {
        this.showNotification('error', error);
      });
    this.router.navigateByUrl('/production');
  }
}
