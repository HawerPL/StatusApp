import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';
import { HomepageComponent } from './components/pages/homepage/homepage.component';
import { LoginComponent } from './components/pages/log/login/login.component';
import { LogoutComponent } from './components/pages/log/logout/logout.component';
import { AuthGuardService } from './services/authGuard/auth-guard.service';
import { SettingsComponent } from './components/pages/settings/settings.component';
import { AdminSettingsComponent } from './components/pages/admin-settings/admin-settings.component';
import { AuthGuardServiceAdminService } from './services/auth/authGuardServiceAdmin/auth-guard-service-admin.service';
import { ProductionsComponent } from './components/pages/productions/productions.component';
import { EditUserComponent } from './components/pages/edit-user/edit-user.component';
import { ProductionComponent } from './components/pages/production/production.component';
import { ApplicationComponent } from './components/pages/application/application.component';
import { ApplicationsStatusComponent } from './components/pages/status/applications-status/applications-status.component';
import { StatusComponent } from './components/pages/status/productions-status/status.component';
import { ErrorStatusComponent } from './components/pages/status/error-status/error-status.component';



const routes: Routes = [
  {
    path: 'home',
    component: HomepageComponent
  },
  {
    path: 'login',
    component: LoginComponent
  },
  {
    path: 'logout',
    component: LogoutComponent,
    canActivate: [AuthGuardService]
  },
  {
    path: 'status',
    component: StatusComponent,
    canActivate: [AuthGuardService]
  },
  {
    path: 'status/:guid',
    component: ApplicationsStatusComponent,
    canActivate: [AuthGuardService]
  },
  {
    path: 'status/:guid/:guid_app',
    component: ErrorStatusComponent,
    canActivate: [AuthGuardService]
  },
  {
    path: 'productions/:guid/:guid_app',
    component: ApplicationComponent,
    canActivate: [AuthGuardServiceAdminService]
  },
  {
    path: 'productions',
    component: ProductionsComponent,
    canActivate: [AuthGuardServiceAdminService]
  },
  {
    path: 'productions/:guid',
    component: ProductionComponent,
    canActivate: [AuthGuardServiceAdminService]
  },
  {
    path: 'settings',
    component: SettingsComponent,
    canActivate: [AuthGuardService]
  },
  {
    path: 'admin-settings',
    component: AdminSettingsComponent,
    canActivate: [AuthGuardServiceAdminService]
  },
  {
    path: 'admin-settings/:id',
    component: EditUserComponent,
    canActivate: [AuthGuardServiceAdminService]
  },
  {
    path: '',
    redirectTo: '/home',
    pathMatch: 'full'
  }
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
