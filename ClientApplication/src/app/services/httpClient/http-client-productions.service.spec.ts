import { TestBed } from '@angular/core/testing';

import { HttpClientProductionsService } from './http-client-productions.service';

describe('HttpClientProductionsService', () => {
  let service: HttpClientProductionsService;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(HttpClientProductionsService);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });
});
