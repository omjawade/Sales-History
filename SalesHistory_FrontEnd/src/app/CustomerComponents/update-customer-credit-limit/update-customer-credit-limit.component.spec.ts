import { ComponentFixture, TestBed } from '@angular/core/testing';

import { UpdateCustomerCreditLimitComponent } from './update-customer-credit-limit.component';

describe('UpdateCustomerCreditLimitComponent', () => {
  let component: UpdateCustomerCreditLimitComponent;
  let fixture: ComponentFixture<UpdateCustomerCreditLimitComponent>;

  beforeEach(() => {
    TestBed.configureTestingModule({
      declarations: [UpdateCustomerCreditLimitComponent]
    });
    fixture = TestBed.createComponent(UpdateCustomerCreditLimitComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
