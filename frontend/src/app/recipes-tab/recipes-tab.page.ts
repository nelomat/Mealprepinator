import { Component } from '@angular/core';
import { Router } from '@angular/router';

@Component({
  selector: 'app-repices-tab',
  templateUrl: 'recipes-tab.page.html',
  styleUrls: ['recipes-tab.page.scss']
})
export class Tab1Page {

  constructor(private router: Router) {}

  navigate(){
    this.router.navigate(['/create-recipe'])
  }
}
