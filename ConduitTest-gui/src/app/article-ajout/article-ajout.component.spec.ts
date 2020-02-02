import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { ArticleAjoutComponent } from './article-ajout.component';

describe('ArticleAjoutComponent', () => {
  let component: ArticleAjoutComponent;
  let fixture: ComponentFixture<ArticleAjoutComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ ArticleAjoutComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(ArticleAjoutComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
