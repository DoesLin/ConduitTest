import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { ChefmagasinModifComponent } from './chefmagasin-modif.component';

describe('ChefmagasinModifComponent', () => {
  let component: ChefmagasinModifComponent;
  let fixture: ComponentFixture<ChefmagasinModifComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ ChefmagasinModifComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(ChefmagasinModifComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
