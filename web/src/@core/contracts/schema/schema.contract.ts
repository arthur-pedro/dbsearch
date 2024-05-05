import { Table } from '../table/table.contract';

export interface Schema {
  name: string;
  tables: Table[];
}

export class SchemaModel implements Schema {
  name: string;
  tables: Table[];

  constructor(name: string, tables: Table[]) {
    this.name = name;
    this.tables = tables;
  }
}
