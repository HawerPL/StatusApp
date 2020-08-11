import { TestBed } from '@angular/core/testing';

import { AuthGuardServiceAdminService } from './auth-guard-service-admin.service';

describe('AuthGuardServiceAdminService', () => {
  let service: AuthGuardServiceAdminService;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(AuthGuardServiceAdminService);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });
});
