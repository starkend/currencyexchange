import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { SymbolsListComponent } from './symbols-list.component';

describe('SymbolsListComponent', () => {
  let component: SymbolsListComponent;
  let fixture: ComponentFixture<SymbolsListComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ SymbolsListComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(SymbolsListComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
