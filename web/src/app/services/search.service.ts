import { Injectable } from '@angular/core';
import { BehaviorSubject, Observable } from 'rxjs';
import { FormControl } from '@angular/forms';
import {
  Filter,
  TableSearch,
} from '../../@core/contracts/table/request/search.contract';
import {
  LOGIC_OPERATOR_SELECT_OPTIONS,
  LogicOperator,
} from '../../@core/types/logic-operator.enum';
import {
  CONDITION_OPERATOR_SELECT_OPTIONS,
  ConditionOperator,
} from '../../@core/types/condition-operatoe.enum';

@Injectable({ providedIn: 'root' })
export class SearchService {
  public searchInTable: FormControl<string | null> = new FormControl<string>(
    ''
  );
  private table: string = '';
  private schema: string = '';
  private page: number = 1;
  private pageSize: number = 10;
  private totalData: number = 0;
  private columns: string[] = [];
  private filters: Map<string, Filter> = new Map();

  private _filters: BehaviorSubject<Map<string, Filter>> = new BehaviorSubject(
    new Map()
  );

  get filters$(): Observable<Map<string, Filter>> {
    return this._filters.asObservable();
  }

  setTable(table: string): void {
    this.table = table;
  }

  getTable(): string {
    return this.table;
  }

  setSchema(schema: string): void {
    this.schema = schema;
  }

  getSchema(): string {
    return this.schema;
  }

  setPage(page: number): void {
    this.page = page;
  }

  getPage(): number {
    return this.page;
  }

  setPageSize(pageSize: number): void {
    this.pageSize = pageSize;
  }

  getPageSize(): number {
    return this.pageSize;
  }

  getTotalData(): number {
    return this.totalData;
  }

  setTotalData(totalData: number): void {
    this.totalData = totalData;
  }

  setColumns(columns: string[]): void {
    columns.map((column) =>
      this.filters.set(column, {
        column,
        value: undefined,
        logicOperator: LogicOperator.AND,
        conditionOperator: ConditionOperator.LIKE,
      })
    );
    this.columns = columns;
  }

  getColumns(): string[] {
    return this.columns;
  }

  getFilters(): Map<string, Filter> {
    return this.filters;
  }

  setFilter(filter: Filter): void {
    this.filters.set(filter.column, filter);
  }

  clear(): void {
    this.table = '';
    this.schema = '';
    this.page = 1;
    this.pageSize = 10;
    this.columns = [];
    this.filters = new Map();
  }

  submit(): void {
    this._filters.next(this.filters);
  }

  getPayload(): TableSearch {
    return TableSearch.fromJSON({
      columns: this.columns,
      filters: this.filters
        ? Array.from(this.filters.values())
        : ([] as Filter[]),
      page: this.page,
      pageSize: this.pageSize,
      schema: this.schema,
      tableName: this.table,
    });
  }

  searchInTableCtrl(): FormControl<string | null> {
    return this.searchInTable;
  }

  get logicOperatorOptions(): { value: LogicOperator; label: string }[] {
    return LOGIC_OPERATOR_SELECT_OPTIONS;
  }

  get conditionOperatorOptions(): {
    value: ConditionOperator;
    label: string;
  }[] {
    return CONDITION_OPERATOR_SELECT_OPTIONS;
  }
}
