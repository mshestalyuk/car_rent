import { TestBed } from '@angular/core/testing';

import { UserDetailsService } from './userdetails.service';

describe('UserdetailsService', () => {
  let service: UserDetailsService;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(UserDetailsService);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });
});
