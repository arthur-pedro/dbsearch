import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { TableSearch } from '../../@core/contracts/table/request/search.contract';
import { TableData } from '../../@core/contracts/table/response/table-data.contract';
import { Table } from '../../@core/contracts/table/response/table.contract';
import { environment } from '../../environments/environment';

@Injectable({ providedIn: 'root' })
export class TableGateway {
  constructor(private readonly http: HttpClient) {}

  list(schema: string): Observable<Table[]> {
    return this.http.get<Table[]>(environment.apiUrl + '/table', {
      params: { schema },
    });
  }

  fechTableColumns(schema: string, table: string): Observable<Table> {
    return this.http.get<Table>(
      environment.apiUrl + `/table/${table}/columns`,
      { params: { schema } }
    );
  }

  fetchTableData(search: TableSearch): Observable<TableData> {
    return this.http.post<TableData>(
      environment.apiUrl + `/table/data`,
      TableSearch.toJSON(search)
    );
  }
}
