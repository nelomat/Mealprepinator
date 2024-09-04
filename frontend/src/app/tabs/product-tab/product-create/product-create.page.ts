import { Component } from '@angular/core';
import { ModalController, NavController } from '@ionic/angular';
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
    private productService: ProductService,
    private navController: NavController
  ) {}

  async dismiss() {
    await this.modalController.dismiss();
  }

  goBack() {
    this.navController.navigateBack('/tabs/product-tab');
  }

  onSubmit() {
    if (!this.newProduct.name || !this.newProduct.quantity || !this.newProduct.unit || !this.newProduct.brand) {
      this.errorMessage = 'Please fill out all fields.';
      return;
    }

    this.productService.createProduct(this.newProduct).subscribe({
      next: (product) => {
        console.log('Product created:', product);
        this.dismiss(); 
      },
      error: (error) => {
        if(error.status == 409) {
          this.errorMessage = 'Conflict: This product already exists';
        } else {
          this.errorMessage = 'Error creating product: ' + error.message;

        }
      },
    });
  }
}
