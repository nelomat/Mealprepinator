import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { Tab1Page } from './recipes-tab.page';
import { CreateRecipeComponent } from './create-recipe/create-recipe.component';

const routes: Routes = [
  {
    path: '',
    component: Tab1Page,
  }

];

@NgModule({
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule]
})
export class Tab1PageRoutingModule {}
