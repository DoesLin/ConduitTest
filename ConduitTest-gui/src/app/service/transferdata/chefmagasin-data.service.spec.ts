import { TestBed } from '@angular/core/testing';

import { ChefmagasinDataService } from './chefmagasin-data.service';

describe('ChefmagasinDataService', () => {
  beforeEach(() => TestBed.configureTestingModule({}));

  it('should be created', () => {
    const service: ChefmagasinDataService = TestBed.get(ChefmagasinDataService);
    expect(service).toBeTruthy();
  });
});
