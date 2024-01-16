import { ComponentFixture, TestBed } from '@angular/core/testing';

import { GetCategorywiseSalesForYearComponent } from './get-categorywise-sales-for-year.component';

describe('GetCategorywiseSalesForYearComponent', () => {
  let component: GetCategorywiseSalesForYearComponent;
  let fixture: ComponentFixture<GetCategorywiseSalesForYearComponent>;

  beforeEach(() => {
    TestBed.configureTestingModule({
      declarations: [GetCategorywiseSalesForYearComponent]
    });
    fixture = TestBed.createComponent(GetCategorywiseSalesForYearComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
