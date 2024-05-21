import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { map, Observable } from 'rxjs';
import { environment } from '../../environments/environment';
import { TableSearch } from '../../@core/contracts/table/request/search.contract';

@Injectable({ providedIn: 'root' })
export class ExportGateway {
  constructor(private readonly http: HttpClient) {}

  export(search: TableSearch): Observable<ArrayBuffer> {
    return this.http
      .post<ArrayBuffer>(
        environment.apiUrl + '/export',
        TableSearch.toJSON(search),
        {
          responseType: 'arraybuffer' as 'json',
        }
      )
      .pipe(
        map((file: ArrayBuffer) => {
          return file;
        })
      );
  }
}
