import { ComponentFixture, TestBed } from '@angular/core/testing';

import { MostsoldproductComponent } from './mostsoldproduct.component';

describe('MostsoldproductComponent', () => {
  let component: MostsoldproductComponent;
  let fixture: ComponentFixture<MostsoldproductComponent>;

  beforeEach(() => {
    TestBed.configureTestingModule({
      declarations: [MostsoldproductComponent]
    });
    fixture = TestBed.createComponent(MostsoldproductComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
