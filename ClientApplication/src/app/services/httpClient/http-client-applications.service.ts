import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';
import { Application } from 'src/app/models/application';
import { environment } from 'src/environments/environment';

@Injectable({
  providedIn: 'root'
})
export class HttpClientApplicationsService {

  constructor(private http: HttpClient) { }

  getApplicationsByGuidProductions(guid: string): Observable<Array<Application>> {
    return this.http.get<Array<Application>>(`${environment.api_url}/applications/${guid}`);
  }

  getApplication(guid: string): Observable<Application> {
    return this.http.get<Application>(`${environment.api_url}/application/${guid}`);
  }

  addApplication(application: Application) {
    return this.http.post(`${environment.api_url}/application`, application);
  }

  deleteApplication(guid_app: string) {
    return this.http.delete(`${environment.api_url}/application/${guid_app}`);
  }
  updateApplication(application: Application) {
    return this.http.put(`${environment.api_url}/application`, application);
  }
}
