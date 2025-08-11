import { ComponentFixture, TestBed } from '@angular/core/testing';

import { PoliceStationComponent } from './police-station.component';

describe('PoliceStationComponent', () => {
  let component: PoliceStationComponent;
  let fixture: ComponentFixture<PoliceStationComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [PoliceStationComponent]
    })
    .compileComponents();

    fixture = TestBed.createComponent(PoliceStationComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
