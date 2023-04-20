import { ComponentFixture, TestBed } from '@angular/core/testing';

import { PregledSvihKorisnikaComponent } from './pregled-svih-korisnika.component';

describe('PregledSvihKorisnikaComponent', () => {
  let component: PregledSvihKorisnikaComponent;
  let fixture: ComponentFixture<PregledSvihKorisnikaComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ PregledSvihKorisnikaComponent ]
    })
    .compileComponents();

    fixture = TestBed.createComponent(PregledSvihKorisnikaComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
