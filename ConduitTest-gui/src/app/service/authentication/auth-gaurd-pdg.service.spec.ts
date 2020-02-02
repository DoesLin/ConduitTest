import { TestBed } from '@angular/core/testing';

import { AuthGaurdPdgService } from './auth-gaurd-pdg.service';

describe('AuthGaurdPdgService', () => {
  beforeEach(() => TestBed.configureTestingModule({}));

  it('should be created', () => {
    const service: AuthGaurdPdgService = TestBed.get(AuthGaurdPdgService);
    expect(service).toBeTruthy();
  });
});
