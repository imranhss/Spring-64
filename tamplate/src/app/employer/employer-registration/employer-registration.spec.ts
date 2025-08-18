import { ComponentFixture, TestBed } from '@angular/core/testing';

import { EmployerRegistration } from './employer-registration';

describe('EmployerRegistration', () => {
  let component: EmployerRegistration;
  let fixture: ComponentFixture<EmployerRegistration>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [EmployerRegistration]
    })
    .compileComponents();

    fixture = TestBed.createComponent(EmployerRegistration);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
