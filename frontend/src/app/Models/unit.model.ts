export class Unit {
    id?: number;
    name?: string;
    abbreviation?: string;
  
    constructor(id?: number, name?: string, abbreviation?: string) {
      this.id = id ?? 0;
      this.name = name ?? '';
      this.abbreviation = abbreviation ?? '';
    }
  }