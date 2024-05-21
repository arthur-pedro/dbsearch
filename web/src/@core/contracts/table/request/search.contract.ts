import { ConditionOperator } from '../../../types/condition-operatoe.enum';
import { LogicOperator } from '../../../types/logic-operator.enum';

export type Filter = {
  column: string;
  value: string | undefined;
  logicOperator: LogicOperator;
  conditionOperator: ConditionOperator;
};

export interface TableSearchProps {
  schema: string;
  tableName: string;
  page: number;
  pageSize: number;
  filters: Filter[];
  columns: string[];
}

export class TableSearch implements TableSearchProps {
  private constructor(private readonly props: TableSearchProps) {}

  get schema(): string {
    return this.props.schema;
  }

  get tableName(): string {
    return this.props.tableName;
  }

  get page(): number {
    return this.props.page;
  }

  get pageSize(): number {
    return this.props.pageSize;
  }

  get filters(): Filter[] {
    return this.props.filters;
  }

  get columns(): string[] {
    return this.props.columns;
  }

  static fromJSON(props: TableSearchProps): TableSearch {
    return new TableSearch(props);
  }

  static toJSON(tableSearch: TableSearch): TableSearchProps {
    return {
      schema: tableSearch.schema,
      tableName: tableSearch.tableName,
      page: tableSearch.page,
      pageSize: tableSearch.pageSize,
      filters: tableSearch.filters,
      columns: tableSearch.columns,
    };
  }
}
