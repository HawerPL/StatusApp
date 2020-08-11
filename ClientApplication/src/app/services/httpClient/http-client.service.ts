import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { User } from 'src/app/models/user';
import { Observable, throwError } from 'rxjs';
import { NotifierService } from 'angular-notifier';
import { environment } from 'src/environments/environment';

@Injectable({
  providedIn: 'root'
})
export class HttpClientService {

  notifier: NotifierService;

  constructor(private http: HttpClient, private notifierService: NotifierService) {
    this.notifier = notifierService;
  }

  public showNotification(type: string, message: string): void {
    this.notifier.notify(type, message);
  }

  getUsers(): Observable<Array<User>> {
    return this.http.get<Array<User>>(`${environment.api_url}/admin-settings/users`);
  }

  getUser(id: number): Observable<User> {
    return this.http.get<User>(`${environment.api_url}/admin-settings/${id}`);
  }

  deleteUser(id: number) {
    return this.http.delete(`${environment.api_url}/admin-settings/${id}`);
  }

  updateUser(user: User) {
    return this.http.put(`${environment.api_url}/admin-settings/user`, user);
  }

  addUser(user: User): Observable<User> {
    return this.http.post<User>(`${environment.api_url}/register`, user);
  }

  updatePassword(user: User) {
    return this.http.patch(`${environment.api_url}/settings`, user);
  }
}
