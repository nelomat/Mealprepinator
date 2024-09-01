import { Component } from '@angular/core';
import { ModalController } from '@ionic/angular';
import { ProductService } from 'src/app/services/product.service';
import { Product } from 'src/app/Models/product.model';

@Component({
  selector: 'app-product-create',
  templateUrl: './product-create.page.html',
  styleUrls: ['./product-create.page.scss'],
})
export class ProductCreatePage {
  newProduct: Product = new Product();
  errorMessage: string = '';

  constructor(
    private modalController: ModalController,
    private productService: ProductService
  ) {}

  async dismiss() {
    await this.modalController.dismiss();
  }

  onSubmit() {
    if (!this.newProduct.name || !this.newProduct.quantity || !this.newProduct.unit || !this.newProduct.brand) {
      this.errorMessage = 'Please fill out all fields.';
      return;
    }

    this.productService.createProduct(this.newProduct).subscribe({
      next: (product) => {
        console.log('Product created:', product);
        this.dismiss(); // Close the modal or navigate back after successful creation
      },
      error: (error) => {
        this.errorMessage = 'Error creating product: ' + error.message;
      },
    });
  }
}
