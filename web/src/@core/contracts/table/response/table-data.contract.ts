export type TableDataUnique = {
  [key: string]: Object;
};

export type TableDataList = TableDataUnique[];

export interface TableData {
  tableName: string;
  columns: string[];
  data: TableDataList;
  page: number;
  size: number;
  totalData: number;
}

export class TableDataModel implements TableData {
  tableName: string;
  columns: string[];
  data: TableDataList;
  page: number;
  size: number;
  totalData: number;

  constructor(
    tableName: string,
    columns: string[],
    data: TableDataList,
    page: number,
    size: number,
    total: number
  ) {
    this.tableName = tableName;
    this.data = data;
    this.columns = columns;
    this.page = page;
    this.size = size;
    this.totalData = total;
  }
}
