import { ComponentFixture, TestBed } from '@angular/core/testing';

import { CreateZahtevZaSertifikatComponent } from './create-zahtev-za-sertifikat.component';

describe('CreateZahtevZaSertifikatComponent', () => {
  let component: CreateZahtevZaSertifikatComponent;
  let fixture: ComponentFixture<CreateZahtevZaSertifikatComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ CreateZahtevZaSertifikatComponent ]
    })
    .compileComponents();

    fixture = TestBed.createComponent(CreateZahtevZaSertifikatComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
