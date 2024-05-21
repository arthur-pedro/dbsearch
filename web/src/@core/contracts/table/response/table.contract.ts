export interface Table {
  tableName: string;
  columns: string[];
}
export class TableModel implements Table {
  tableName: string;
  columns: string[];

  constructor(tableName: string, columns: string[]) {
    this.tableName = tableName;
    this.columns = columns;
  }
}
