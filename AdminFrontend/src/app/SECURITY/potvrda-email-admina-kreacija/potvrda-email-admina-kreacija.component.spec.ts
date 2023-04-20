import { ComponentFixture, TestBed } from '@angular/core/testing';

import { PotvrdaEmailAdminaKreacijaComponent } from './potvrda-email-admina-kreacija.component';

describe('PotvrdaEmailAdminaKreacijaComponent', () => {
  let component: PotvrdaEmailAdminaKreacijaComponent;
  let fixture: ComponentFixture<PotvrdaEmailAdminaKreacijaComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ PotvrdaEmailAdminaKreacijaComponent ]
    })
    .compileComponents();

    fixture = TestBed.createComponent(PotvrdaEmailAdminaKreacijaComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
