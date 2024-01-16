import { ComponentFixture, TestBed } from '@angular/core/testing';

import { GetCountOfCustomersComponent } from './get-count-of-customers.component';

describe('GetCountOfCustomersComponent', () => {
  let component: GetCountOfCustomersComponent;
  let fixture: ComponentFixture<GetCountOfCustomersComponent>;

  beforeEach(() => {
    TestBed.configureTestingModule({
      declarations: [GetCountOfCustomersComponent]
    });
    fixture = TestBed.createComponent(GetCountOfCustomersComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
