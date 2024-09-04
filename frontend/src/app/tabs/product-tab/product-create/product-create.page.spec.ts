import { ComponentFixture, TestBed } from '@angular/core/testing';
import { ProductCreatePage } from './product-create.page';
import { IonicModule, ModalController } from '@ionic/angular';
import { FormsModule } from '@angular/forms';
import { HttpClientTestingModule, HttpTestingController  } from '@angular/common/http/testing';
import { ProductService } from 'src/app/services/product.service';
import { of, throwError } from 'rxjs';

class MockModalController {
  dismiss = jasmine.createSpy('dismiss');
}

class MockProductService {
  createProduct = jasmine.createSpy('createProduct').and.returnValue(of({}));
}

describe('ProductCreatePage', () => {
  let component: ProductCreatePage;
  let fixture: ComponentFixture<ProductCreatePage>;
  let productService: ProductService;
  let modalController: ModalController;
  let httpTestingController: HttpTestingController;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ProductCreatePage],
      imports: [IonicModule.forRoot(), FormsModule, HttpClientTestingModule],
      providers: [
        { provide: ProductService, useClass: MockProductService },
        { provide: ModalController, useClass: MockModalController },
      ],
    }).compileComponents();

    fixture = TestBed.createComponent(ProductCreatePage);
    component = fixture.componentInstance;
    productService = TestBed.inject(ProductService);
    modalController = TestBed.inject(ModalController);
    httpTestingController = TestBed.inject(HttpTestingController);
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });

  it('should call createProduct and dismiss the modal on successful submission', () => {
    component.newProduct = { name: 'Apple', quantity: 1, unit: 'Piece', brand: 'Ja!' };
    component.onSubmit();

    expect(productService.createProduct).toHaveBeenCalledWith(component.newProduct);
    expect(modalController.dismiss).toHaveBeenCalled();
  });

  it('should display error message if product already exists', () => {
    const errorResponse = { status: 409, message: 'Product exists' };
    productService.createProduct = jasmine.createSpy('createProduct').and.returnValue(throwError(errorResponse));

    component.onSubmit();

    expect(component.errorMessage).toContain('This product already exists');
    expect(modalController.dismiss).not.toHaveBeenCalled();
  });

  it('should display generic error message on other errors', () => {
    const errorResponse = { status: 400, message: 'Bad Request' };
    productService.createProduct = jasmine.createSpy('createProduct').and.returnValue(throwError(errorResponse));

    component.onSubmit();

    expect(component.errorMessage).toContain('Bad Request');
    expect(modalController.dismiss).not.toHaveBeenCalled();
  });

  it('should reset errorMessage on successful submission', () => {
    component.errorMessage = 'Some error';
    component.newProduct = { name: 'Test', quantity: 1, unit: 'Stück', brand: 'Brand' };
    
    component.onSubmit();
    
    expect(component.errorMessage).toBe('');
  });
});
