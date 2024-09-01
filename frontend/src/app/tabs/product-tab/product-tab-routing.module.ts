import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';

import { ProductTabPage } from './product-tab.page';

const routes: Routes = [
  {
    path: 'tabs',
    children: [
      {
        path: 'product-tab',
        loadChildren: () => import('../product-tab/product-tab.module').then(m => m.ProductTabPageModule)
      },
      // andere Routen
    ]
  },
  {
    path: 'product-create',
    loadChildren: () => import('./product-create/product-create.module').then( m => m.ProductCreatePageModule)
  },
  // andere Routen
];


@NgModule({
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule],
})
export class ProductTabPageRoutingModule {}
