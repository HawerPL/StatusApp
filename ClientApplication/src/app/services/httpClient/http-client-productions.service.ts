import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';
import { Production } from 'src/app/models/production';
import { environment } from 'src/environments/environment';

@Injectable({
  providedIn: 'root'
})
export class HttpClientProductionsService {

  constructor(private http: HttpClient) { }

  getProductions(): Observable<Array<Production>>{
    return this.http.get<Array<Production>>(`${environment.api_url}/productions`);
  }

  getProduction(guid: string): Observable<Production>{
    return this.http.get<Production>(`${environment.api_url}/production/${guid}`);
  }

  addProduction(production: Production){
    return this.http.post(`${environment.api_url}/production`, production);
  }

  deleteProduction(guid: string){
    return this.http.delete(`${environment.api_url}/production/${guid}`);
  }

  updateProduction(production: Production){
    return this.http.put(`${environment.api_url}/production`, production);
  }

}
