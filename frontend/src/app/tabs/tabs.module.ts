import { IonicModule } from '@ionic/angular';
import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { FormsModule } from '@angular/forms';

import { TabsPageRoutingModule } from './tabs-routing.module';

import { TabsPage } from './tabs.page';
import { Tab1PageRoutingModule } from '../recipes-tab/recipes-tab-routing.module';
import { ProductTabPage } from './product-tab/product-tab.page';
import { RouterModule, Routes } from '@angular/router';

const routes: Routes = [
  {
    path: '',
    component: TabsPage,
    children: [
      {
        path: 'product-tab',
        component: ProductTabPage,
      },
    ],
  },
];
@NgModule({
  imports: [
    IonicModule,
    CommonModule,
    FormsModule,
    TabsPageRoutingModule,
    Tab1PageRoutingModule,
    RouterModule.forChild(routes),
  ],
  declarations: [TabsPage]
})
export class TabsPageModule {}
