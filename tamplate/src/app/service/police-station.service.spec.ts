import { TestBed } from '@angular/core/testing';

import { PoliceStationService } from './police-station.service';

describe('PoliceStationService', () => {
  let service: PoliceStationService;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(PoliceStationService);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });
});
