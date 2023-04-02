import { ComponentFixture, TestBed } from '@angular/core/testing';

import { ViewZahtevZaSertifikatComponent } from './view-zahtev-za-sertifikat.component';

describe('ViewZahtevZaSertifikatComponent', () => {
  let component: ViewZahtevZaSertifikatComponent;
  let fixture: ComponentFixture<ViewZahtevZaSertifikatComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ ViewZahtevZaSertifikatComponent ]
    })
    .compileComponents();

    fixture = TestBed.createComponent(ViewZahtevZaSertifikatComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
