import { Injectable, Signal, WritableSignal, signal } from '@angular/core';
import { BehaviorSubject, Observable } from 'rxjs';

export type Where = Map<string, string | undefined>;

@Injectable({ providedIn: 'root' })
export class SearchService {
  private table: string = '';
  private schema: string = '';
  private page: number = 1;
  private pageSize: number = 10;
  private columns: string[] = [];
  private filters: Where = new Map();
  private _where: BehaviorSubject<Where> = new BehaviorSubject(new Map());

  get where(): Observable<Where> {
    return this._where.asObservable();
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

  setColumns(columns: string[]): void {
    columns.map((column) => this.filters.set(column, undefined));
    this.columns = columns;
  }

  getColumns(): string[] {
    return this.columns;
  }

  getFilter(column: string): string | undefined {
    return this.filters.get(column);
  }

  setFilter(column: string, value: string | undefined): void {
    this.filters.set(column, value);
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
    this._where.next(this.filters);
  }
}
