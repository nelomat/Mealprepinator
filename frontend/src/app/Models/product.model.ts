import { Brand } from "./brand.model";
import { Unit } from "./unit.model";

export class Product {
  id?: number;
  name!: string;
  brand!: Brand;
  unit!: Unit;
  quantity!: number;
  

  constructor(id?: number, name?: string, brand?: Brand, quantity?: number, unit?: Unit ) {
    this.id = id ?? 0; 
    this.name = name ?? ''; 
    this.brand = brand ?? new Brand(); 
    this.quantity = quantity ?? 0;
    this.unit = unit ?? new Unit(); 
  }
}