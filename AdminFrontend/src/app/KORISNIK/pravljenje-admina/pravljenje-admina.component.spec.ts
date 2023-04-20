import { ComponentFixture, TestBed } from '@angular/core/testing';

import { PravljenjeAdminaComponent } from './pravljenje-admina.component';

describe('PravljenjeAdminaComponent', () => {
  let component: PravljenjeAdminaComponent;
  let fixture: ComponentFixture<PravljenjeAdminaComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ PravljenjeAdminaComponent ]
    })
    .compileComponents();

    fixture = TestBed.createComponent(PravljenjeAdminaComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
