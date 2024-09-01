export class Product {
  id?: number;
  name!: string;
  quantity!: number;
  unit!: string;
  brand!: string;

  constructor(id?: number, name?: string, quantity?: number, unit?: string, brand?: string) {
    this.id = id ?? 0; 
    this.name = name ?? ''; 
    this.quantity = quantity ?? 0;
    this.unit = unit ?? ''; 
    this.brand = brand ?? ''; 
  }
}