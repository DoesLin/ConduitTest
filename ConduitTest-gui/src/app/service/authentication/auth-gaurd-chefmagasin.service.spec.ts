import { TestBed } from '@angular/core/testing';

import { AuthGaurdChefmagasinService } from './auth-gaurd-chefmagasin.service';

describe('AuthGaurdChefmagasinService', () => {
  beforeEach(() => TestBed.configureTestingModule({}));

  it('should be created', () => {
    const service: AuthGaurdChefmagasinService = TestBed.get(AuthGaurdChefmagasinService);
    expect(service).toBeTruthy();
  });
});
