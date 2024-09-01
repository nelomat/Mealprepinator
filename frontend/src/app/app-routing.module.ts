import { NgModule } from '@angular/core';
import { PreloadAllModules, RouterModule, Routes } from '@angular/router';
import { TabsPage } from './tabs/tabs.page';

const routes: Routes = [
  {
    path: '',
    loadChildren: () => import('./tabs/tabs.module').then(m => m.TabsPageModule)
  },
  {
    path: 'create-recipe',
    loadChildren: () => import('./recipes-tab/recipes-tab.module').then(m => m.Tab1PageModule)
  },
  {
    path: 'tabs',
    component: TabsPage,
    children: [
      {
        path: 'recipes-tab',
        loadChildren: () => import('src/app/recipes-tab/recipes-tab.module').then(m => m.Tab1PageModule)
      },
      {
        path: 'mealprep-tab',
        loadChildren: () => import('src/app/mealprep-tab/tab2.module').then(m => m.Tab2PageModule)
      },
    {
        path: 'product-tab',
        loadChildren: () => import('src/app/tabs/product-tab/product-tab.module').then( m => m.ProductTabPageModule)
      },
      {
        path: 'tab3',
        loadChildren: () => import('src/app/tab3/tab3.module').then(m => m.Tab3PageModule)
      },
    ]
  },
];

@NgModule({
  imports: [
    RouterModule.forRoot(routes, { preloadingStrategy: PreloadAllModules })
  ],
  exports: [RouterModule]
})
export class AppRoutingModule {}
