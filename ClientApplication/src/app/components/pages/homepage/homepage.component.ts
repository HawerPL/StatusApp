import { Component, OnInit } from '@angular/core';
import { NotifierService } from 'angular-notifier';

@Component({
  selector: 'app-homepage',
  templateUrl: './homepage.component.html',
  styleUrls: ['./homepage.component.css']
})
export class HomepageComponent implements OnInit {

  private notifier: NotifierService;

  constructor(notifierService: NotifierService) {
    this.notifier = notifierService;
   }

  ngOnInit(): void {
  }

  
}
