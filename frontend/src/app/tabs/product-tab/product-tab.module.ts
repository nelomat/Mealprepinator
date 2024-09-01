import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { FormsModule } from '@angular/forms';

import { IonicModule } from '@ionic/angular';

import { ProductTabPageRoutingModule } from './product-tab-routing.module';

import { ProductTabPage } from './product-tab.page';
import { HttpClientModule } from '@angular/common/http';

@NgModule({
  imports: [
    CommonModule,
    FormsModule,
    IonicModule,
    ProductTabPageRoutingModule,
    HttpClientModule
  ],
  declarations: [ProductTabPage]
})
export class ProductTabPageModule {}
