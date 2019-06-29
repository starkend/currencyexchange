import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { HistoricalRatesComponent } from './historical-rates.component';

describe('HistoricalRatesComponent', () => {
  let component: HistoricalRatesComponent;
  let fixture: ComponentFixture<HistoricalRatesComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ HistoricalRatesComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(HistoricalRatesComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
