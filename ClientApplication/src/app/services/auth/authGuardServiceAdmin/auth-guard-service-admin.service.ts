import { Injectable } from '@angular/core';
import { ActivatedRouteSnapshot, RouterStateSnapshot, Router, CanActivate } from '@angular/router';
import { AuthenticationService } from '../authentication.service';
import { AESEncryptDecryptService } from '../../AESEncryptDecryptService/aesencrypt-decrypt.service';

@Injectable({
  providedIn: 'root'
})
export class AuthGuardServiceAdminService implements CanActivate{

  constructor(private router: Router, private authService: AuthenticationService, private aesEncryptDecryptService: AESEncryptDecryptService) { }

  canActivate(route: ActivatedRouteSnapshot, state: RouterStateSnapshot){
    if(this.aesEncryptDecryptService.decrypt(sessionStorage.getItem('role')) == 'admin'){
      return true;
    }
    else{
      return false;
    }
  }
}
