import { ComponentFixture, TestBed } from '@angular/core/testing';

import { ChannelAggregateSaleComponent } from './channel-aggregate-sale.component';

describe('ChannelAggregateSaleComponent', () => {
  let component: ChannelAggregateSaleComponent;
  let fixture: ComponentFixture<ChannelAggregateSaleComponent>;

  beforeEach(() => {
    TestBed.configureTestingModule({
      declarations: [ChannelAggregateSaleComponent]
    });
    fixture = TestBed.createComponent(ChannelAggregateSaleComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
