import { ComponentFixture, TestBed } from '@angular/core/testing';

import { GetCategorywiseSalesComponent } from './get-categorywise-sales.component';

describe('GetCategorywiseSalesComponent', () => {
  let component: GetCategorywiseSalesComponent;
  let fixture: ComponentFixture<GetCategorywiseSalesComponent>;

  beforeEach(() => {
    TestBed.configureTestingModule({
      declarations: [GetCategorywiseSalesComponent]
    });
    fixture = TestBed.createComponent(GetCategorywiseSalesComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
