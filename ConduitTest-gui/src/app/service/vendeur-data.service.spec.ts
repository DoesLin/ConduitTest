import { TestBed } from '@angular/core/testing';

import { VendeurDataService } from './vendeur-data.service';

describe('VendeurDataService', () => {
  beforeEach(() => TestBed.configureTestingModule({}));

  it('should be created', () => {
    const service: VendeurDataService = TestBed.get(VendeurDataService);
    expect(service).toBeTruthy();
  });
});
