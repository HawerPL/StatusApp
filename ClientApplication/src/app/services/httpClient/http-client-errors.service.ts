import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';
import { ErrorE } from 'src/app/models/error';
import { environment } from 'src/environments/environment';

@Injectable({
  providedIn: 'root'
})
export class HttpClientErrorsService {

  constructor(private http: HttpClient) { }


  getErrorsByGuidApp(guid_app: string): Observable<Array<ErrorE>>{
    return this.http.get<Array<ErrorE>>(`${environment.api_url}/errors/${guid_app}`);
  }
}
