import { ComponentFixture, TestBed } from '@angular/core/testing';

import { ShowsalesComponent } from './showsales.component';

describe('ShowsalesComponent', () => {
  let component: ShowsalesComponent;
  let fixture: ComponentFixture<ShowsalesComponent>;

  beforeEach(() => {
    TestBed.configureTestingModule({
      declarations: [ShowsalesComponent]
    });
    fixture = TestBed.createComponent(ShowsalesComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
