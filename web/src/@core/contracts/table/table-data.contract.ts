export interface TableData {
  tableName: string;
  columns: string[];
  data: {
    [key: string]: Object;
  }[];
}
export class TableDataModel implements TableData {
  tableName: string;
  columns: string[];
  data: {
    [key: string]: Object;
  }[];

  constructor(
    tableName: string,
    columns: string[],
    data: {
      [key: string]: Object;
    }[]
  ) {
    this.tableName = tableName;
    this.data = data;
    this.columns = columns;
  }
}
