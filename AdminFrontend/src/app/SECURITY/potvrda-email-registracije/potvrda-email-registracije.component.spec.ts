import { ComponentFixture, TestBed } from '@angular/core/testing';

import { PotvrdaEmailRegistracijeComponent } from './potvrda-email-registracije.component';

describe('PotvrdaEmailRegistracijeComponent', () => {
  let component: PotvrdaEmailRegistracijeComponent;
  let fixture: ComponentFixture<PotvrdaEmailRegistracijeComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ PotvrdaEmailRegistracijeComponent ]
    })
    .compileComponents();

    fixture = TestBed.createComponent(PotvrdaEmailRegistracijeComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
