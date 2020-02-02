import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { ChefmagasinAjoutComponent } from './chefmagasin-ajout.component';

describe('ChefmagasinAjoutComponent', () => {
  let component: ChefmagasinAjoutComponent;
  let fixture: ComponentFixture<ChefmagasinAjoutComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ ChefmagasinAjoutComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(ChefmagasinAjoutComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
