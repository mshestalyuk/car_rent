import { TestBed } from '@angular/core/testing';

import { DriverLicenseService } from './driverlicense.service';

describe('DriverlicenseService', () => {
  let service: DriverLicenseService;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(DriverLicenseService);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });
});
