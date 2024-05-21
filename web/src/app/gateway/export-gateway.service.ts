import { HttpClient, HttpParams } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { TableData } from '../../@core/contracts/table/table-data.contract';
import { Table } from '../../@core/contracts/table/table.contract';
import { environment } from '../../environments/environment';
import { Where } from '../services/search.service';

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

  fetchTableData(
    schema: string,
    table: string,
    where: Where,
    page: {
      page: number;
      pageSize: number;
    }
  ): Observable<TableData> {
    let params = new HttpParams();
    params = params.append('page', page.page);
    params = params.append('size', page.pageSize);
    params = params.append('schema', schema);
    const dataObject = Object.fromEntries(where);
    return this.http.post<TableData>(
      environment.apiUrl + `/table/${table}/data`,
      dataObject,
      { params: params }
    );
  }
}
