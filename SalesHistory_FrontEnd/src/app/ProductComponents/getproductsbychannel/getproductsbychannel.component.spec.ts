import { ComponentFixture, TestBed } from '@angular/core/testing';

import { GetproductsbychannelComponent } from './getproductsbychannel.component';

describe('GetproductsbychannelComponent', () => {
  let component: GetproductsbychannelComponent;
  let fixture: ComponentFixture<GetproductsbychannelComponent>;

  beforeEach(() => {
    TestBed.configureTestingModule({
      declarations: [GetproductsbychannelComponent]
    });
    fixture = TestBed.createComponent(GetproductsbychannelComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
