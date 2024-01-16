import { ComponentFixture, TestBed } from '@angular/core/testing';

import { AddcountryComponent } from './addcountry.component';

describe('AddcountryComponent', () => {
  let component: AddcountryComponent;
  let fixture: ComponentFixture<AddcountryComponent>;

  beforeEach(() => {
    TestBed.configureTestingModule({
      declarations: [AddcountryComponent]
    });
    fixture = TestBed.createComponent(AddcountryComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
