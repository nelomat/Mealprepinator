import { RouterModule, Routes } from '@angular/router';
import { NgModule } from '@angular/core';

import { TabsPage } from './tabs.page';

const routes: Routes = [
  {
    path: 'tabs',
    component: TabsPage,
    children: [
      {
        path: 'recipes-tab',
        loadChildren: () => import('../recipes-tab/recipes-tab.module').then(m => m.Tab1PageModule)
      },
      {
        path: 'mealprep-tab',
        loadChildren: () => import('../mealprep-tab/tab2.module').then(m => m.Tab2PageModule)
      },
    {
        path: 'product-tab',
        loadChildren: () => import('./product-tab/product-tab.module').then( m => m.ProductTabPageModule)
      },
      {
        path: 'tab3',
        loadChildren: () => import('../tab3/tab3.module').then(m => m.Tab3PageModule)
      },
      {
        path: '',
        redirectTo: '',
        pathMatch: 'full'
      }
    ]
  },
  {
    path: '',
    redirectTo: '/recipes-tab',
    pathMatch: 'full'
  },
];

@NgModule({
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule]
})
export class TabsPageRoutingModule {}
