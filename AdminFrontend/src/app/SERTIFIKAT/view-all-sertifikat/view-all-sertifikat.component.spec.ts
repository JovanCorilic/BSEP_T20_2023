import { ComponentFixture, TestBed } from '@angular/core/testing';

import { ViewAllSertifikatComponent } from './view-all-sertifikat.component';

describe('ViewAllSertifikatComponent', () => {
  let component: ViewAllSertifikatComponent;
  let fixture: ComponentFixture<ViewAllSertifikatComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ ViewAllSertifikatComponent ]
    })
    .compileComponents();

    fixture = TestBed.createComponent(ViewAllSertifikatComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
