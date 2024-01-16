import { ComponentFixture, TestBed } from '@angular/core/testing';

import { ChannelaggregaterevenueComponent } from './channelaggregaterevenue.component';

describe('ChannelaggregaterevenueComponent', () => {
  let component: ChannelaggregaterevenueComponent;
  let fixture: ComponentFixture<ChannelaggregaterevenueComponent>;

  beforeEach(() => {
    TestBed.configureTestingModule({
      declarations: [ChannelaggregaterevenueComponent]
    });
    fixture = TestBed.createComponent(ChannelaggregaterevenueComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
