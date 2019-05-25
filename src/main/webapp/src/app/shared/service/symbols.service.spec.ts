import { TestBed } from '@angular/core/testing';

import { SymbolsService } from './symbols.service';

describe('SymbolsService', () => {
  beforeEach(() => TestBed.configureTestingModule({}));

  it('should be created', () => {
    const service: SymbolsService = TestBed.get(SymbolsService);
    expect(service).toBeTruthy();
  });
});
