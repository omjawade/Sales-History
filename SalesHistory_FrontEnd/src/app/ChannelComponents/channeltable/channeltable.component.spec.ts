import { ComponentFixture, TestBed } from '@angular/core/testing';

import { ChanneltableComponent } from './channeltable.component';

describe('ChanneltableComponent', () => {
  let component: ChanneltableComponent;
  let fixture: ComponentFixture<ChanneltableComponent>;

  beforeEach(() => {
    TestBed.configureTestingModule({
      declarations: [ChanneltableComponent]
    });
    fixture = TestBed.createComponent(ChanneltableComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
