import { ComponentFixture, TestBed } from '@angular/core/testing';

import { Addstudent } from './addstudent';

describe('Addstudent', () => {
  let component: Addstudent;
  let fixture: ComponentFixture<Addstudent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [Addstudent]
    })
    .compileComponents();

    fixture = TestBed.createComponent(Addstudent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
