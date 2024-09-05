import { Component } from '@angular/core';
import { NavController } from '@ionic/angular';

@Component({
  selector: 'app-tabs',
  templateUrl: 'tabs.page.html',
  styleUrls: ['tabs.page.scss']
})
export class TabsPage {

  constructor(
    private navController: NavController
  ) { }

  goToProductTab() {
    this.navController.navigateBack('/product-tab');
  }
}
