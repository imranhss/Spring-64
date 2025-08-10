import { TestBed } from '@angular/core/testing';

import { ExtracurricularService } from './extracurricular.service';

describe('ExtracurricularService', () => {
  let service: ExtracurricularService;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(ExtracurricularService);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });
});
