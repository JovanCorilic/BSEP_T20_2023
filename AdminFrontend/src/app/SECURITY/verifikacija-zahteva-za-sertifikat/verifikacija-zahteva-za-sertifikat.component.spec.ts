import { ComponentFixture, TestBed } from '@angular/core/testing';

import { VerifikacijaZahtevaZaSertifikatComponent } from './verifikacija-zahteva-za-sertifikat.component';

describe('VerifikacijaZahtevaZaSertifikatComponent', () => {
  let component: VerifikacijaZahtevaZaSertifikatComponent;
  let fixture: ComponentFixture<VerifikacijaZahtevaZaSertifikatComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ VerifikacijaZahtevaZaSertifikatComponent ]
    })
    .compileComponents();

    fixture = TestBed.createComponent(VerifikacijaZahtevaZaSertifikatComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
