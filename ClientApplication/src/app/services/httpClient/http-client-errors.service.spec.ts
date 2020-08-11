import { TestBed } from '@angular/core/testing';

import { HttpClientErrorsService } from './http-client-errors.service';

describe('HttpClientErrorsService', () => {
  let service: HttpClientErrorsService;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(HttpClientErrorsService);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });
});
