import { ComponentFixture, TestBed } from '@angular/core/testing';

import { ShowcountryComponent } from './showcountry.component';

describe('ShowcountryComponent', () => {
  let component: ShowcountryComponent;
  let fixture: ComponentFixture<ShowcountryComponent>;

  beforeEach(() => {
    TestBed.configureTestingModule({
      declarations: [ShowcountryComponent]
    });
    fixture = TestBed.createComponent(ShowcountryComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
