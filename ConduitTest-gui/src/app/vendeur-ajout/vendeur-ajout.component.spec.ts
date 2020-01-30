import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { VendeurAjoutComponent } from './vendeur-ajout.component';

describe('VendeurAjoutComponent', () => {
  let component: VendeurAjoutComponent;
  let fixture: ComponentFixture<VendeurAjoutComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ VendeurAjoutComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(VendeurAjoutComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
