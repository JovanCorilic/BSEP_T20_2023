import { ComponentFixture, TestBed } from '@angular/core/testing';

import { ViewSertifikatComponent } from './view-sertifikat.component';

describe('ViewSertifikatComponent', () => {
  let component: ViewSertifikatComponent;
  let fixture: ComponentFixture<ViewSertifikatComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ ViewSertifikatComponent ]
    })
    .compileComponents();

    fixture = TestBed.createComponent(ViewSertifikatComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
