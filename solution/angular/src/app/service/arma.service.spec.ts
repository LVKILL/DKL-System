import { TestBed } from '@angular/core/testing';

import { ArmaService } from './arma.service';

describe('ArmaService', () => {
  beforeEach(() => TestBed.configureTestingModule({}));

  it('should be created', () => {
    const service: ArmaService = TestBed.get(ArmaService);
    expect(service).toBeTruthy();
  });
});
