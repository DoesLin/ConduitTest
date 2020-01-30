import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { ArticleModifComponent } from './article-modif.component';

describe('ArticleModifComponent', () => {
  let component: ArticleModifComponent;
  let fixture: ComponentFixture<ArticleModifComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ ArticleModifComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(ArticleModifComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
