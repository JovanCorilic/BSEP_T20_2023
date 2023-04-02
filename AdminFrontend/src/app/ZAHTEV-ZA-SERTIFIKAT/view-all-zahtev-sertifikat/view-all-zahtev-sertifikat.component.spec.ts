import { ComponentFixture, TestBed } from '@angular/core/testing';

import { ViewAllZahtevSertifikatComponent } from './view-all-zahtev-sertifikat.component';

describe('ViewAllZahtevSertifikatComponent', () => {
  let component: ViewAllZahtevSertifikatComponent;
  let fixture: ComponentFixture<ViewAllZahtevSertifikatComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ ViewAllZahtevSertifikatComponent ]
    })
    .compileComponents();

    fixture = TestBed.createComponent(ViewAllZahtevSertifikatComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
