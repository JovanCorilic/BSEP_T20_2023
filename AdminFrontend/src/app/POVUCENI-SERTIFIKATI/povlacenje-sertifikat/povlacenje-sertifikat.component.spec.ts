import { ComponentFixture, TestBed } from '@angular/core/testing';

import { PovlacenjeSertifikatComponent } from './povlacenje-sertifikat.component';

describe('PovlacenjeSertifikatComponent', () => {
  let component: PovlacenjeSertifikatComponent;
  let fixture: ComponentFixture<PovlacenjeSertifikatComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ PovlacenjeSertifikatComponent ]
    })
    .compileComponents();

    fixture = TestBed.createComponent(PovlacenjeSertifikatComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
