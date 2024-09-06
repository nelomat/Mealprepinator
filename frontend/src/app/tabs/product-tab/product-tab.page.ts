import { Component, OnInit } from '@angular/core';
import { ProductService } from '../../services/product.service';
import { NavController, ToastController } from '@ionic/angular';
import { Product } from '../../Models/product.model';
@Component({
  selector: 'app-product-tab',
  templateUrl: './product-tab.page.html',
  styleUrls: ['./product-tab.page.scss'],
})
export class ProductTabPage implements OnInit {
  products: Product[] = [];
  filteredProducts: Product[] = [];
  searchTerm: string = '';
  errorMessage: string = '';
  loading: boolean = true;
  noProductsMessage: string = 'No products available.';

  constructor(private productService: ProductService, 
    private toastController: ToastController,
    private navController: NavController,
  ) {
    this.filteredProducts = this.products;
  }

  ngOnInit() {
    this.loadProducts();
  }

  loadProducts(event?: any) {
    this.productService.getAllProducts().subscribe({
      next: (data: Product[]) => {
        console.log(data);
        this.products = data.sort((a, b) => a.name.localeCompare(b.name));;
        this.filteredProducts = [...this.products];
        this.loading = false;
        if (event) {
          event.target.complete(); 
        }
      },
      error: (error) => {
        this.errorMessage = 'Error loading products: ' + error.message;
        this.loading = false;
        if (event) {
          event.target.complete(); 
        }
      },
    });
  }
  //@TODO make search bar a component, remove console logs 
  filterProducts(event: any) {
    const searchTerm = event.target.value.toLowerCase();
    console.log('Suchbegriff:', searchTerm);
    console.log('Alle Produkte:', this.products);
    this.searchTerm = searchTerm; 

    if (searchTerm) {
    this.filteredProducts = this.products.filter(product => 
      product.name.toLowerCase().includes(searchTerm) ||
      product.brand?.name?.toLowerCase().includes(searchTerm) || 
      product.unit?.name?.toLowerCase().includes(searchTerm) || 
      product.quantity?.toString().includes(searchTerm)
    ); } else {
      this.filteredProducts = [...this.products.sort((a, b) => a.name.localeCompare(b.name))];
    
    console.log('Gefilterte Produkte:', this.filteredProducts);
    }
  }

  // @TODO doesnt work properly yet if product is used in recipe 
  async deleteProduct(productId: number) {
    try {
      await this.productService.deleteProduct(productId).toPromise();
      this.products = this.products.filter(product => product.id !== productId);
      this.presentToast('Product deleted successfully');
    } catch (error) {
      this.presentToast('Failed to delete product');
    }
  }

  async presentToast(message: string) {
    const toast = await this.toastController.create({
      message,
      duration: 2000
    });
    toast.present();
  }

  
  isOdd(product: Product): boolean {
    return this.products.indexOf(product) % 2 !== 0;
  }

  goToProductDetails(id: number | undefined) {
    this.navController.navigateForward(['/tabs/product-detail', id]);
  }
  goToProductCreate() {
    this.navController.navigateBack('/tabs/product-tab/product-create');
  }
}  
