import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { ChefmagasinsComponent } from './chefmagasins.component';

describe('ChefmagasinsComponent', () => {
  let component: ChefmagasinsComponent;
  let fixture: ComponentFixture<ChefmagasinsComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ ChefmagasinsComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(ChefmagasinsComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
