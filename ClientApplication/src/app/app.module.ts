import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';

import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { NavbarComponent } from './components/navbar/navbar/navbar.component';
import { HomepageComponent } from './components/pages/homepage/homepage.component';
import { LoginComponent } from './components/pages/log/login/login.component';
import { LogoutComponent } from './components/pages/log/logout/logout.component';
import { HttpClientModule, HTTP_INTERCEPTORS } from '@angular/common/http';
import { FormsModule } from '@angular/forms';
import { SettingsComponent } from './components/pages/settings/settings.component';
import { AdminSettingsComponent } from './components/pages/admin-settings/admin-settings.component';
import { ProductionsComponent } from './components/pages/productions/productions.component';
import { NotifierModule, NotifierOptions } from "angular-notifier";
import { ApplicationsComponent } from './components/pages/applications/applications.component';
import { ProductionComponent } from './components/pages/production/production.component';
import { ApplicationComponent } from './components/pages/application/application.component';
import { EditUserComponent } from './components/pages/edit-user/edit-user.component';
import { NgbModule } from '@ng-bootstrap/ng-bootstrap';
import { HttpInterceptorService } from './services/HttpInterceptor/http-interceptor.service';
import { IconsComponent } from './components/icons/icons.component';
import { ApplicationsStatusComponent } from './components/pages/status/applications-status/applications-status.component';
import { StatusComponent } from './components/pages/status/productions-status/status.component';
import { ErrorsComponent } from './components/pages/errors/errors.component';
import { ErrorStatusComponent } from './components/pages/status/error-status/error-status.component';

const customNotifierOptions: NotifierOptions = {
  position: {
    horizontal: {
      position: 'left',
      distance: 12
    },
    vertical: {
      position: 'bottom',
      distance: 80,
      gap: 10
    }
  },
  theme: 'material',
  behaviour: {
    autoHide: 5000,
    onClick: false,
    onMouseover: 'pauseAutoHide',
    showDismissButton: true,
    stacking: 4
  },
  animations: {
    enabled: true,
    show: {
      preset: 'slide',
      speed: 300,
      easing: 'ease'
    },
    hide: {
      preset: 'fade',
      speed: 300,
      easing: 'ease',
      offset: 50
    },
    shift: {
      speed: 300,
      easing: 'ease'
    },
    overlap: 150
  }
};

@NgModule({
  declarations: [
    AppComponent,
    LoginComponent,
    NavbarComponent,
    HomepageComponent,
    LogoutComponent,
    StatusComponent,
    SettingsComponent,
    AdminSettingsComponent,
    ProductionsComponent,
    ApplicationsComponent,
    ProductionComponent,
    ApplicationComponent,
    EditUserComponent,
    IconsComponent,
    ApplicationsStatusComponent,
    ErrorsComponent,
    ErrorStatusComponent
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    HttpClientModule,
    FormsModule,
    NotifierModule.withConfig(customNotifierOptions),
    NgbModule
  ],
  providers: [{
    provide: HTTP_INTERCEPTORS, useClass: HttpInterceptorService, multi: true
  }],
  bootstrap: [AppComponent]
})
export class AppModule { }
