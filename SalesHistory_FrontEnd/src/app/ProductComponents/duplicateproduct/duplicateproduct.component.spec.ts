import { ComponentFixture, TestBed } from '@angular/core/testing';

import { DuplicateproductComponent } from './duplicateproduct.component';

describe('DuplicateproductComponent', () => {
  let component: DuplicateproductComponent;
  let fixture: ComponentFixture<DuplicateproductComponent>;

  beforeEach(() => {
    TestBed.configureTestingModule({
      declarations: [DuplicateproductComponent]
    });
    fixture = TestBed.createComponent(DuplicateproductComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
