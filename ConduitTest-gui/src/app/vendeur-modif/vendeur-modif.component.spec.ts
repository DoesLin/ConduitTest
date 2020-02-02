import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { VendeurModifComponent } from './vendeur-modif.component';

describe('VendeurModifComponent', () => {
  let component: VendeurModifComponent;
  let fixture: ComponentFixture<VendeurModifComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ VendeurModifComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(VendeurModifComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
