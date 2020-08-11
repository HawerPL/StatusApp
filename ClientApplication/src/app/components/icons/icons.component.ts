import { Component, OnInit, DoCheck } from '@angular/core';
import {Location} from '@angular/common';
import { AuthenticationService } from 'src/app/services/auth/authentication.service';

@Component({
  selector: 'app-icons',
  templateUrl: './icons.component.html',
  styleUrls: ['./icons.component.css']
})
export class IconsComponent implements DoCheck{

  show: boolean = false;

  constructor(private _location: Location, private authService: AuthenticationService) { }
  
  ngDoCheck(): void {
    if(this.authService.isUserLoggedIn()){
      this.show = true;
    }
  }

  backClicked(){
    this._location.back();
  }

  refreshPage(){
    window.location.reload();
  }
}
