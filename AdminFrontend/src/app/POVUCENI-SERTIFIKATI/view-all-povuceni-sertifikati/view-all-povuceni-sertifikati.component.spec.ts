import { ComponentFixture, TestBed } from '@angular/core/testing';

import { ViewAllPovuceniSertifikatiComponent } from './view-all-povuceni-sertifikati.component';

describe('ViewAllPovuceniSertifikatiComponent', () => {
  let component: ViewAllPovuceniSertifikatiComponent;
  let fixture: ComponentFixture<ViewAllPovuceniSertifikatiComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ ViewAllPovuceniSertifikatiComponent ]
    })
    .compileComponents();

    fixture = TestBed.createComponent(ViewAllPovuceniSertifikatiComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
