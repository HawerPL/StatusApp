import { Component, OnInit, Input } from '@angular/core';
import { ErrorE } from 'src/app/models/error';
import { HttpClientErrorsService } from 'src/app/services/httpClient/http-client-errors.service';
import { NotifierService } from 'angular-notifier';

@Component({
  selector: 'app-errors',
  templateUrl: './errors.component.html',
  styleUrls: ['./errors.component.css']
})
export class ErrorsComponent implements OnInit {


  @Input() guid_app: string;
  error: ErrorE;
  errorsArray: Array<ErrorE>;
  notifier: NotifierService;

  constructor(private http: HttpClientErrorsService, private notifierSerivce: NotifierService) { 
    this.notifier = notifierSerivce;
  }

  ngOnInit(): void {
    this.getErrors();
  }

  public showNotification(type: string, message: string): void {
    this.notifier.notify(type, message);
  }
  
  getErrors(){
    this.http.getErrorsByGuidApp(this.guid_app).subscribe(res =>{
      this.errorsArray = res;
    },
    error =>{
      this.showNotification('error', error);
    });
  }

}
