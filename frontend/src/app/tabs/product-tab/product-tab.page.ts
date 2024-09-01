import { Component, OnInit } from '@angular/core';
import { ProductService } from '../../services/product.service';
import { ModalController } from '@ionic/angular';
import { Product } from '../../Models/product.model';
import { ProductCreatePage } from './product-create/product-create.page';
@Component({
  selector: 'app-product-tab',
  templateUrl: './product-tab.page.html',
  styleUrls: ['./product-tab.page.scss'],
})
export class ProductTabPage implements OnInit {
  products: Product[] = [];
  errorMessage: string = '';
  loading: boolean = true;
  noProductsMessage: string = 'No products available.';

  constructor(private productService: ProductService, 
    private modalController: ModalController
  ) {}

  ngOnInit() {
    this.loadProducts();
  }

  loadProducts(event?: any) {
    this.productService.getAllProducts().subscribe({
      next: (data: Product[]) => {
        console.log(data);
        this.products = data;
        this.loading = false;
        if (event) {
          event.target.complete(); // Vervollständigt den Pull-to-Refresh
        }
      },
      error: (error) => {
        this.errorMessage = 'Error loading products: ' + error.message;
        this.loading = false;
        if (event) {
          event.target.complete(); // Vervollständigt den Pull-to-Refresh
        }
      },
    });
  }
  async openCreateProductModal() {
    const modal = await this.modalController.create({
      component: ProductCreatePage,
    });
    await modal.present();
  }
}  
