import { async, ComponentFixture, TestBed } from '@angular/core/testing';
import { ProductTabPage } from './product-tab.page';

describe('ProductTabPage', () => {
  let component: ProductTabPage;
  let fixture: ComponentFixture<ProductTabPage>;

  beforeEach(async(() => {
    fixture = TestBed.createComponent(ProductTabPage);
    component = fixture.componentInstance;
    fixture.detectChanges();
  }));

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
