import { ComponentFixture, TestBed } from '@angular/core/testing';

import { AddjobseekerComponent } from './addjobseeker.component';

describe('AddjobseekerComponent', () => {
  let component: AddjobseekerComponent;
  let fixture: ComponentFixture<AddjobseekerComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [AddjobseekerComponent]
    })
    .compileComponents();

    fixture = TestBed.createComponent(AddjobseekerComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
