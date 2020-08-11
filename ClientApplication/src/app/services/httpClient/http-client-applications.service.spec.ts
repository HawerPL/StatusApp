import { TestBed } from '@angular/core/testing';

import { HttpClientApplicationsService } from './http-client-applications.service';

describe('HttpClientApplicationsService', () => {
  let service: HttpClientApplicationsService;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(HttpClientApplicationsService);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });
});
