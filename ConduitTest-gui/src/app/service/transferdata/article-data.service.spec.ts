import { TestBed } from '@angular/core/testing';

import { ArticleDataService } from './article-data.service';

describe('ArticleDataService', () => {
  beforeEach(() => TestBed.configureTestingModule({}));

  it('should be created', () => {
    const service: ArticleDataService = TestBed.get(ArticleDataService);
    expect(service).toBeTruthy();
  });
});
